package com.czl.xyyq.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 请假审批表
 * @TableName leave_apply
 */
@TableName(value ="leave_apply")
@Data
public class LeaveApply implements Serializable {
    /**
     * id
     */
    @TableId(value = "id")
    private String id;

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
     * 宿舍号
     */
    @TableField(value = "dormitory_no")
    private String dormitoryNo;

    /**
     * 请假原因
     */
    @TableField(value = "reason")
    private String reason;

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
     * 请假时间区间
     */
    @TableField(value = "time")
    private String time;

    /**
     * 请假天数
     */
    @TableField(value = "day")
    private String day;

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
     * 附件
     */
    @TableField(value = "img")
    private String img;

    /**
     * 交通工具
     */
    @TableField(value = "traffic")
    private String traffic;

    /**
     * 紧急联系人电话
     */
    @TableField(value = "emergency_telephone_number")
    private String emergencyTelephoneNumber;

    /**
     * 紧急联系人
     */
    @TableField(value = "emergency_contact")
    private String emergencyContact;

    /**
     * 审核意见
     */
    @TableField(value = "opinion")
    private String opinion;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    @TableField(value = "is_delete")
    private Boolean isDelete;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}