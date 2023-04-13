package com.czl.xyyq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czl.xyyq.model.entity.HealthSm;
import com.czl.xyyq.model.entity.User;

/**
 *健康上报服务
 */
public interface HealthSmService extends IService<HealthSm> {

    /**
     * 修改健康上报信息
     * @param healthSm
     * @param loginUser
     * @return
     */
    int updateHealthSm(HealthSm healthSm, User loginUser);
}
