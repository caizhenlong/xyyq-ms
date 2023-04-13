package com.czl.xyyq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.mapper.MaterialInfoMapper;
import com.czl.xyyq.model.entity.MaterialInfo;
import com.czl.xyyq.model.entity.MaterialOut;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.service.MaterialInfoService;
import com.czl.xyyq.utils.AdminUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class MaterialInfoServiceImpl extends ServiceImpl<MaterialInfoMapper, MaterialInfo>
        implements MaterialInfoService {

    @Resource
    private MaterialInfoMapper materialInfoMapper;

    /**
     * 修改物资信息
     *
     * @param MaterialInfo
     * @param loginUser
     * @return
     */
    @Override
    public int updateMaterialInfo(MaterialInfo MaterialInfo, User loginUser) {
        if (MaterialInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer id = MaterialInfo.getId();
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //如果是管理员，允许更新任意用户
        //如果不是管理员，不允许更新信息
        if (!AdminUtil.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }

        MaterialInfo oldMaterialInfo = materialInfoMapper.selectById(id);
        if (oldMaterialInfo == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return materialInfoMapper.updateById(MaterialInfo);

    }

    /**
     * 物资出库（修改库存）
     *
     * @param materialOut  物资
     * @return
     */
    @Override
    public void updateTotal(MaterialOut materialOut) {
        String name = materialOut.getName();
        Integer quantity = materialOut.getQuantity();
        materialInfoMapper.updateTotal(name,quantity);
    }


}




