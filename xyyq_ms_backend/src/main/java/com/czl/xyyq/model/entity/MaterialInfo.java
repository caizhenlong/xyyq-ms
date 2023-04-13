package com.czl.xyyq.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 物资信息表
 * @TableName material_info
 */
@TableName(value ="material_info")
@Data
public class MaterialInfo implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 物资图片
     */
    @TableField(value = "img")
    private String img;

    /**
     * 物资规格
     */
    @TableField(value = "specification")
    private String specification;

    /**
     * 物资单位
     */
    @TableField(value = "unit")
    private String unit;

    /**
     * 创建人
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 备注
     */
    @TableField(value = "description")
    private String description;

    /**
     * 库存
     */
    @TableField(value = "total")
    private Integer total;

    /**
     * 是否启用 0-不可用 1-可用
     */
    @TableField(value = "status")
    private Integer status;

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