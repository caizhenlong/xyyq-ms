package com.czl.xyyq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czl.xyyq.model.entity.Notice;
import com.czl.xyyq.model.entity.User;

/**
 * 公告接口
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 更改公告信息
     * @param notice
     * @param loginUser
     * @return
     */
    int updateNotice(Notice notice, User loginUser);
}
