package com.czl.xyyq.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author caizhenlong
 * @create 2023/3/3
 */
@Data
public class MaterialInfoQueryDto {

    /**
     * 物资类型名称
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 物资名称
     */
    @TableField(value = "name")
    private String name;


    /**
     * 创建人
     */
    @TableField(value = "creator")
    private String creator;


}
