package com.czl.xyyq.service;

import com.czl.xyyq.model.entity.User;
import org.apache.http.HttpRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author caizhenlong
 * @create 2023/3/31
 * 用户服务测试
 */
class UserServiceTest {

    @Resource
    private UserService userService;

    private HttpServletRequest request;

    /**
     * 账号不能为空；用户账户长度不能小于5，密码长度不能小于6;账户不能包含特殊字符,只能是数字
     */
    @Test
    void doLogin() {

    }

    @Test
    void userLogout() {
    }

    @Test
    void userRegister() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getLoginUser() {
    }

    @Test
    void getSafeUser() {
    }
}