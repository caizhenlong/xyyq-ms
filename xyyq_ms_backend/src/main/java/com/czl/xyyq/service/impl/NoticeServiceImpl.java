package com.czl.xyyq.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.mapper.NoticeMapper;
import com.czl.xyyq.model.entity.LeaveApply;
import com.czl.xyyq.model.entity.Notice;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.service.NoticeService;
import com.czl.xyyq.utils.AdminUtil;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    /**
     * 更改公告信息
     * @param notice
     * @param loginUser
     * @return
     */
    @Override
    public int updateNotice(Notice notice, User loginUser) {
        if (notice == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer id = notice.getId();
        if (id==null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //如果是管理员和教师，允许更新任意公告信息
        //如果不是管理员和教师，不允许更新信息
        if (!AdminUtil.isAdminOrTeacher(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }

        Notice oldNoticeInfo = noticeMapper.selectById(id);
        if (oldNoticeInfo == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return noticeMapper.updateById(notice);
    }
}




