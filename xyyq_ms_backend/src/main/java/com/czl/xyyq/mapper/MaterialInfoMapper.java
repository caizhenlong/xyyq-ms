package com.czl.xyyq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czl.xyyq.model.entity.MaterialInfo;

/**
 * @Entity generator.domain.MaterialInfo
 */
public interface MaterialInfoMapper extends BaseMapper<MaterialInfo> {

    /**
     * 物资出库（修改material_info表的库存）
     * @param name 物资名称，已做唯一索引
     * @param quantity 出库数量
     * @return
     */
    void updateTotal(String name, Integer quantity);
}




