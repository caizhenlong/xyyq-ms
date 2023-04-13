package com.czl.xyyq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czl.xyyq.model.entity.LeaveApply;
import com.czl.xyyq.model.entity.User;

/**
 * 请假申请接口
 */
public interface LeaveApplyService extends IService<LeaveApply> {

    /**
     * 修改请假申请
     * @param leaveApply
     * @param loginUser
     * @return
     */
    int updateLeaveApplyInfo(LeaveApply leaveApply, User loginUser);
}
