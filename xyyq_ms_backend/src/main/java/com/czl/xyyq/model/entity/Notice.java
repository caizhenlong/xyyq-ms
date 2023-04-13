package com.czl.xyyq.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告表
 * @TableName notice
 */
@TableName(value ="notice")
@Data
public class Notice implements Serializable {
    /**
     * id
     */
    @TableField(value = "id")
    private int id;

    /**
     * 公告内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 发布者
     */
    @TableField(value = "publisher")
    private String publisher;

    /**
     * 公告类型 0-普通通知 1-讲座通知 2-重要公告
     */
    @TableField(value = "type")
    private Integer type;

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