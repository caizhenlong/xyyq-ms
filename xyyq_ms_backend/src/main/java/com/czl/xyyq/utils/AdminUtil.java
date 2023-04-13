package com.czl.xyyq.utils;

import com.czl.xyyq.model.entity.User;

import javax.servlet.http.HttpServletRequest;

import static com.czl.xyyq.constant.UserConstant.*;

/**
 * @author caizhenlong
 * @create 2023/3/3
 */
public class AdminUtil {


    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    public static boolean isAdmin(HttpServletRequest request) {
        //仅管理员可删除
        User userObject = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        return userObject != null && userObject.getUserRole() == ADMIN_ROLE;
    }

    /**
     * 是否为管理员
     *
     * @param loginUser
     * @return
     */
    public static boolean isAdmin(User loginUser) {
        //仅管理员可删除
        return loginUser != null && loginUser.getUserRole() == ADMIN_ROLE;
    }

    /**
     * 教师和管理员开放，学生不开放
     *
     * @param request
     * @return
     */
    public static boolean isAdminOrTeacher(HttpServletRequest request) {
        User userObject = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        return userObject != null && userObject.getUserRole() == ADMIN_ROLE || userObject.getUserRole() == Teacher_ROLE;
    }

    /**
     * 是否为管理员
     *
     * @param loginUser
     * @return
     */
    public static boolean isAdminOrTeacher(User loginUser) {
        //仅管理员和教师可删除
        return loginUser != null && loginUser.getUserRole() == ADMIN_ROLE || loginUser.getUserRole() == Teacher_ROLE;
    }
}
