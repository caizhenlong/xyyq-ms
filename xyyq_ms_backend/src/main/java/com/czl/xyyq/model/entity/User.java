package com.czl.xyyq.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 性别  1-男 2-女
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 微信昵称
     */
    @TableField(value = "nike_name")
    private String nikeName;

    /**
     * 用户头像
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户状态（0-禁用，1-正常）
     */
    @TableField(value = "user_status")
    private Integer userStatus;

    /**
     * 用户角色 0-学生 1-老师 2-管理员
     */
    @TableField(value = "user_role")
    private Integer userRole;

    /**
     * 院系
     */
    @TableField(value = "department")
    private String department;

    /**
     * 班级
     */
    @TableField(value = "classes")
    private String classes;

    /**
     * 宿舍号
     */
    @TableField(value = "dormitory_no")
    private String dormitoryNo;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(value = "is_delete")
    private Byte isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}