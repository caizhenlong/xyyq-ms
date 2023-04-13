package com.czl.xyyq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czl.xyyq.common.BaseResponse;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.common.ResultUtils;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.model.dto.HealthSmQueryDto;
import com.czl.xyyq.model.dto.NoticeQueryDto;
import com.czl.xyyq.model.entity.HealthSm;
import com.czl.xyyq.model.entity.Notice;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.service.NoticeService;
import com.czl.xyyq.service.UserService;
import com.czl.xyyq.utils.AdminUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公告接口
 *
 * @author caizhenlong.
 * @create 2023/3/13
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeservice;

    @Resource
    UserService userService;

    /**
     * 发布公告信息
     *
     * @param notice
     * @param request
     * @return
     */
    @PostMapping("/addNoticeInfo")
    public BaseResponse<Integer> addNoticeInfo(@RequestBody Notice notice, HttpServletRequest request) {

        //仅管理员或教师可发布
        if (!AdminUtil.isAdminOrTeacher(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }


        if (notice == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //获取登录的用户,作为填写者
        User loginUser = userService.getLoginUser(request);
        String username = loginUser.getUsername();
        notice.setPublisher(username);

        String content = notice.getContent();
        Integer type = notice.getType();

        if (StringUtils.isEmpty(content)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (type < 0 || type==null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean saveResult = noticeservice.save(notice);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SAVA_ERROR, "保存失败");
        }
        Integer result = notice.getId();
        return ResultUtils.success(result);
    }


    /**
     * 查询所有公告信息
     * @param noticeQueryDto
     * @param request
     * @return
     */
    @GetMapping("/search")
    public BaseResponse<List<Notice>> searchNotices(NoticeQueryDto noticeQueryDto, HttpServletRequest request) {

        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();

        String publisher = noticeQueryDto.getPublisher();
        Integer type = noticeQueryDto.getType();


        if (StringUtils.isNotEmpty(publisher)) {
            queryWrapper.like("publisher", publisher);
        }
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        List<Notice> list = noticeservice.list(queryWrapper);
        return ResultUtils.success(list);
    }

    @GetMapping("list")
    public BaseResponse<List<Notice>> list(){
        List<Notice> list = noticeservice.list();
        return ResultUtils.success(list);
    }



    @PostMapping("/update")
    public BaseResponse<Integer> updateNotice(@RequestBody Notice notice,HttpServletRequest request){
        //1.校验参数是否为空
        if (notice==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2.校验权限 管理员和教师可更新任意公告信息，普通用户不能修改
        User loginUser = userService.getLoginUser(request);
        //3.触发更新
        int result = noticeservice.updateNotice(notice,loginUser);
        return ResultUtils.success(result);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteNotice(@RequestBody Integer id,HttpServletRequest request) {
        if (!AdminUtil.isAdminOrTeacher(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id==null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"公告编号错误");
        }
        boolean result = noticeservice.removeById(id);
        return ResultUtils.success(result);

    }
}
