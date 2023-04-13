package com.czl.xyyq.constant;

/**
 * 用户常量
 *
 * @author caizhenlong
 * @create 2023/2/10
 */
public interface UserConstant {

    /**
     * 盐值
     */
    String SALT = "caicai";

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "userLoginState";

    /**
     * 默认权限--学生
     */
    int DEFAULT_ROLE = 0;

    /**
     * 教师权限
     */
    int Teacher_ROLE = 1;

    /**
     * 管理员权限
     */
    int ADMIN_ROLE = 2;

    String DEFAULT_AVATAR_URL = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202107%2F19%2F20210719150601_4401e.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1683884002&t=0f740b3511bc1685b54d8180d604dde5";
}
