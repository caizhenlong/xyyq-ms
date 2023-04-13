package com.czl.xyyq.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName material_out
 */
@TableName(value ="material_out")
@Data
public class MaterialOut implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    /**
     * 出库事由
     */
    @TableField(value = "reason")
    private String reason;

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