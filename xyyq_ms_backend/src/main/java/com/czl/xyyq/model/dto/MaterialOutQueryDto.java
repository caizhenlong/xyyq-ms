package com.czl.xyyq.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author caizhenlong
 * @create 2023/3/3
 */
@Data
public class MaterialOutQueryDto {

    /**
     * 物资名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 出库数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 出库负责人
     */
    @TableField(value = "superintendent")
    private String superintendent;

}
