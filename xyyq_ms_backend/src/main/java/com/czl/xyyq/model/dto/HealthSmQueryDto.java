package com.czl.xyyq.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author caizhenlong
 * @create 2023/3/3
 */
@Data
public class HealthSmQueryDto {
    /**
     * uid 学号
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 身体状况 1（正常），2（有发热、咳嗽、呼吸困难等症状），3（其他症状）
     */
    @TableField(value = "physical_condition")
    private Integer physicalCondition;

    /**
     * 1-在校 0-不在校
     */
    @TableField(value = "at_school")
    private Integer atSchool;

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
}
