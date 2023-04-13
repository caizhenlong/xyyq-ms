package com.czl.xyyq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czl.xyyq.model.entity.MaterialInfo;
import com.czl.xyyq.model.entity.MaterialOut;
import com.czl.xyyq.model.entity.User;

/**
 * 物资管理服务
 */
public interface MaterialInfoService extends IService<MaterialInfo> {
    /**
     * 修改物资信息
     * @param materialInfo
     * @param loginUser
     * @return
     */
    int updateMaterialInfo(MaterialInfo materialInfo, User loginUser);

    /**
     * 物资出库（修改material_info表的库存）
     * @param materialOut 出库物资
     * @return
     */
    void updateTotal( MaterialOut materialOut);
}
