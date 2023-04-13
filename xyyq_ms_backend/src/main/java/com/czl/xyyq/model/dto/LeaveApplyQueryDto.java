package com.czl.xyyq.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author caizhenlong
 * @create 2023/3/3
 */
@Data
public class LeaveApplyQueryDto {

    /**
     * 学生学号
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 学生姓名
     */
    @TableField(value = "username")
    private String username;

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
     * 请假类型（1：事假 2：病假 3：公假  4：申请校外实习）
     */
    @TableField(value = "leave_type")
    private Integer leaveType;

    /**
     * 状态（0：撤销 1：待审核 2：审核通过 3：审核不通过）
     */
    @TableField(value = "status")
    private Integer status;


    /**
     * 目前所在地
     */
    @TableField(value = "location")
    private String location;

    /**
     * 目的地
     */
    @TableField(value = "address")
    private String address;


    /**
     * 交通工具
     */
    @TableField(value = "traffic")
    private String traffic;

    /**
     * 审核意见
     */
    @TableField(value = "opinion")
    private String opinion;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;



}
