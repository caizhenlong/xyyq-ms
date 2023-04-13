package com.czl.xyyq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.mapper.LeaveApplyMapper;
import com.czl.xyyq.model.entity.LeaveApply;
import com.czl.xyyq.model.entity.MaterialInfo;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.service.LeaveApplyService;
import com.czl.xyyq.utils.AdminUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class LeaveApplyServiceImpl extends ServiceImpl<LeaveApplyMapper, LeaveApply>
    implements LeaveApplyService {

    @Resource
    private LeaveApplyMapper leaveApplyMapper;

    /**
     * 修改请假申请
     * @param leaveApply
     * @param loginUser
     * @return
     */
    @Override
    public int updateLeaveApplyInfo(LeaveApply leaveApply, User loginUser) {
        if (leaveApply == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String id = leaveApply.getId();
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //如果是管理员和教师，允许更新任意用户
        //如果不是管理员，不允许更新信息
        if (!AdminUtil.isAdminOrTeacher(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }

        LeaveApply oldLeaveApplyInfo = leaveApplyMapper.selectById(id);
        if (oldLeaveApplyInfo == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return leaveApplyMapper.updateById(leaveApply);
    }
}




