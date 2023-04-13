package com.czl.xyyq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czl.xyyq.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 *用户服务
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录
     * @param id 用户账户/学号
     * @param userPassword 用户密码
     * @return 返回用户信息
     */
    User doLogin(String id, String userPassword, HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     * 用户注册
     *
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @param planetCode 星球编号
     * @return 新用户id
     */
    String userRegister(String userAccount, String userPassword, String checkPassword,String planetCode);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user,User loginUser);

    /**
     * 获取当前登录用户信息
     * @return
     */
    User getLoginUser(HttpServletRequest request);


    /**
     * 用户信息脱敏
     * @param user 用户
     * @return 用户脱敏信息
     */
    User getSafeUser(User user);

}
