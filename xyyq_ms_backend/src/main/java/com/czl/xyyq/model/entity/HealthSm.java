package com.czl.xyyq.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 健康上报表
 * @TableName health_sm
 */
@TableName(value ="health_sm")
@Data
public class HealthSm implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * uid 学号
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 姓名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 身体状况 1（正常），2（有发热、咳嗽、呼吸困难等症状），3（其他症状）
     */
    @TableField(value = "physical_condition")
    private Integer physicalCondition;

    /**
     * 现在所在地
     */
    @TableField(value = "location")
    private String location;

    /**
     * 高风险 1（true）是， 0（false）否
     */
    @TableField(value = "is_high_risk")
    private Integer isHighRisk;

    /**
     * 健康码状态 1（green）绿码， 2（yellow）黄码，3（red）红码
     */
    @TableField(value = "health_code_status")
    private Integer healthCodeStatus;

    /**
     * 1-在校 0-不在校
     */
    @TableField(value = "at_school")
    private Integer atSchool;

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