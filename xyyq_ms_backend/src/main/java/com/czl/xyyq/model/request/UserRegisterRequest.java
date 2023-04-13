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
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 5165892698942490437L;

    private String id;
    private String username;
    private String userPassword;
    private String checkPassword;
}
