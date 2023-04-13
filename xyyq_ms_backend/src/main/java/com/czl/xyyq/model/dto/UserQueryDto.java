package com.czl.xyyq.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author caizhenlong
 * @create 2023/3/1
 */
@Data
public class UserQueryDto {

    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;


}
