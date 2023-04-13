package com.czl.xyyq.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author caizhenlong
 * @create 2023/3/14
 */
@Data
public class NoticeQueryDto {
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
}
