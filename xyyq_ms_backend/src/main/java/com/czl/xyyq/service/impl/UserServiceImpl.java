package com.czl.xyyq.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.constant.UserConstant;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.mapper.UserMapper;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.service.UserService;
import com.czl.xyyq.utils.AdminUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.czl.xyyq.constant.UserConstant.*;

/**
 * 用户服务实现类
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param id           用户账户/学号
     * @param userPassword 用户密码
     * @param request
     * @return
     */
    @Override
    public User doLogin(String id, String userPassword, HttpServletRequest request) {
        //1.校验
        if (StringUtils.isAnyBlank(id, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号名或密码不能为空");
        }
        if (id.length() < 5) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }

        //账户不能包含特殊字符,只能是数字
        String validPattern = "^[+]{1,2}(\\d+)$";
        Matcher matcher = Pattern.compile(validPattern).matcher(id);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号包含其他字符");
        }

        //2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("user_password", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);

        //用户不存在
        if (user == null) {
            log.info("user login failed，userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号名或密码不正确");
        }

        //判断用户是否是被禁用
        if (user.getUserStatus() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户已禁用");
        }

        //3. 返回用户信息（脱敏）
        User safetyUser = getSafeUser(user);

        //4.记录用户登录态
        HttpSession session = request.getSession();
        session.setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    /**
     * 用户注册
     *
     * @param id
     * @param username
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return
     */
    @Override
    public String userRegister(String id, String username, String userPassword, String checkPassword) {
        //1.校验
        if (StringUtils.isBlank(id)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或学号不能为空");
        }
        if (StringUtils.isBlank(userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能为空");
        }
        if (StringUtils.isBlank(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "确认密码不能为空");
        }
        if (StringUtils.isBlank(username)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "姓名不能为空");
        }
        if (id.length() < 5) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }

        if (userPassword.length() < 6 || checkPassword.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度过短");
        }

        //姓名不能包含特殊字符
        String validPattern = ".*[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>《》？/?~！@#￥%……&*()——+|{}【】‘；：”“’。，、？\\\\\\\\]+.*";
        Matcher matcher = Pattern.compile(validPattern).matcher(username);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "姓名不能包含特殊字符");
        }

        //密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码和确认密码不一致");
        }

        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户已存在");
        }

        //2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //3.插入数据
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setUserPassword(encryptPassword);
        user.setAvatarUrl(DEFAULT_AVATAR_URL);  //设置默认头像
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SAVA_ERROR, "保存失败");
        }

        return user.getId();
    }


    @Override
    public int updateUser(User user, User loginUser) {
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userId = user.getId();
        String loginUserId = loginUser.getId();
        if (!StringUtils.isNotBlank(userId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //如果是管理员，允许更新任意用户
        //如果不是管理员，只允许修改个人的基本信息
        System.out.println(AdminUtil.isAdmin(loginUser));
        if (!AdminUtil.isAdmin(loginUser) && userId.equals(loginUserId)) {
            User oldUser = userMapper.selectById(userId);
            if (oldUser == null) {
                throw new BusinessException(ErrorCode.NULL_ERROR);
            }
            return userMapper.updateById(user);

        }else if(AdminUtil.isAdmin(loginUser)){
            User oldUser = userMapper.selectById(userId);
            if (oldUser == null) {
                throw new BusinessException(ErrorCode.NULL_ERROR);
            }
            return userMapper.updateById(user);
        }
        else{
            throw new BusinessException(ErrorCode.NO_AUTH,"无权限");
        }
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        if (userObj == null) {
            /*throw new BusinessException(ErrorCode.NO_AUTH);*/
            throw new BusinessException(ErrorCode.NO_LOGIN);
        }
        return (User) userObj;

    }


    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

    /**
     * 用户信息脱敏
     *
     * @param user 用户
     * @return 用户脱敏信息
     */
    @Override
    public User getSafeUser(User user) {

        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setNikeName(user.getNikeName());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setUserRole(user.getUserRole());
        safetyUser.setDepartment(user.getDepartment());
        safetyUser.setClasses(user.getClasses());
        safetyUser.setDormitoryNo(user.getDormitoryNo());
        safetyUser.setCreateTime(user.getCreateTime());
        safetyUser.setIsDelete(user.getIsDelete());
        return safetyUser;
    }

}




