package com.czl.xyyq.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author caizhenlong
 * @create 2023/2/10
 */
@Data
    public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 4940410557179124985L;
    private String id;
    private String userPassword;
}
