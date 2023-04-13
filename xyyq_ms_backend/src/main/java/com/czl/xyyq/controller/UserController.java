package com.czl.xyyq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czl.xyyq.common.BaseResponse;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.common.ResultUtils;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.model.request.UserLoginRequest;
import com.czl.xyyq.model.request.UserRegisterRequest;
import com.czl.xyyq.service.UserService;
import com.czl.xyyq.utils.AdminUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.czl.xyyq.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 * @author caizhenlong.
 * @create 2023/2/23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String id = userLoginRequest.getId();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(id, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.doLogin(id, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/register")
    public BaseResponse<String> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String id = userRegisterRequest.getId();
        String username = userRegisterRequest.getUsername();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(id, username,userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String result = userService.userRegister(id,username, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/currentUser")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        User currentUser = (User)request.getSession().getAttribute(USER_LOGIN_STATE);
        if (currentUser==null){
            throw new BusinessException(ErrorCode.NO_LOGIN,"未登录");
        }
        String id = currentUser.getId();
        //TODO 检验用户是否合法
        User user = userService.getById(id);
        User safeUser = userService.getSafeUser(user);
        return ResultUtils.success(safeUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (!AdminUtil.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /*if (StringUtils.isNotEmpty(userQueryDto.getId())) {
            queryWrapper.like("id", userQueryDto.getId());
        }*/
        if (StringUtils.isNotEmpty(username)) {
            queryWrapper.like("username", username);
        }
        List<User> list = userService.list(queryWrapper);
        //查询到内存中进行判断，也可在数据库里面查询
        List<User> userList = list.stream().map(user -> {
            return userService.getSafeUser(user);
        }).collect(Collectors.toList());

        return ResultUtils.success(userList);
    }


    @PostMapping("/update")
    public BaseResponse<Integer> updateUser(@RequestBody User user,HttpServletRequest request){
        //1.校验参数是否为空
        if (user==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2.校验权限 管理员可更新任意用户，普通用户只能修改自己
        User loginUser = userService.getLoginUser(request);
        //3.触发更新
        int result = userService.updateUser(user,loginUser);
        return ResultUtils.success(result);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody String id,HttpServletRequest request) {
        if (!AdminUtil.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (!StringUtils.isNotBlank(id) || Integer.parseInt(id)<0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"学号错误");
        }
        boolean result = userService.removeById(id);
        return ResultUtils.success(result);

    }
}
