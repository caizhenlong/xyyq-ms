package com.czl.xyyq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.mapper.HealthSmMapper;
import com.czl.xyyq.mapper.UserMapper;
import com.czl.xyyq.model.entity.HealthSm;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.service.HealthSmService;
import com.czl.xyyq.utils.AdminUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class HealthSmServiceImpl extends ServiceImpl<HealthSmMapper, HealthSm>
    implements HealthSmService {

    @Resource
    private HealthSmMapper healthSmMapper;

    /**
     * 修改健康上报信息
     * @param healthSm
     * @param loginUser
     * @return
     */
    @Override
    public int updateHealthSm(HealthSm healthSm, User loginUser) {
        if (healthSm==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer id = healthSm.getId();
        if (id==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //如果是管理员和教师，允许更新任意用户
        //如果不是管理员，不允许更新信息
        if (!AdminUtil.isAdminOrTeacher(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }

        HealthSm oldHealthSm = healthSmMapper.selectById(id);
        if (oldHealthSm == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return healthSmMapper.updateById(healthSm);

    }
}




