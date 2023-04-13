package com.czl.xyyq.controller;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czl.xyyq.common.BaseResponse;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.common.ResultUtils;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.model.dto.LeaveApplyQueryDto;
import com.czl.xyyq.model.dto.MaterialInfoQueryDto;
import com.czl.xyyq.model.entity.LeaveApply;
import com.czl.xyyq.model.entity.MaterialInfo;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.service.LeaveApplyService;
import com.czl.xyyq.service.UserService;
import com.czl.xyyq.utils.AdminUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 请假接口
 * @author caizhenlong
 * @create 2023/3/11
 */
@RestController
@RequestMapping("/leave")
public class LeaveApplyController {

    @Resource
    private LeaveApplyService leaveApplyService;

    @Resource
    UserService userService;

    /**
     * 添加请假申请信息
     *
     * @param leaveApply
     * @param request
     * @return
     */
    @PostMapping("/addLeaveApply")
    public BaseResponse<String> addLeaveApply(@RequestBody LeaveApply leaveApply, HttpServletRequest request) {
        if (leaveApply == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //获取登录的用户,作为填写者
        User loginUser = userService.getLoginUser(request);
        String userId = loginUser.getId();
        String username = loginUser.getUsername();
        String department = loginUser.getDepartment();
        String classes = loginUser.getClasses();
        String dormitoryNo = loginUser.getDormitoryNo();

        leaveApply.setUid(userId);
        leaveApply.setUsername(username);
        leaveApply.setDepartment(department);
        leaveApply.setClasses(classes);
        leaveApply.setDormitoryNo(dormitoryNo);

        String reason = leaveApply.getReason();
        Integer leaveType = leaveApply.getLeaveType();
        String time = leaveApply.getTime();
        String address = leaveApply.getAddress();
        String traffic = leaveApply.getTraffic();
        String phoneNumber = leaveApply.getEmergencyTelephoneNumber();
        String emergencyContact = leaveApply.getEmergencyContact();
        String location = leaveApply.getLocation();

        if (StringUtils.isAnyBlank(reason,time,address,traffic,phoneNumber,emergencyContact,location)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (leaveType<0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean saveResult = leaveApplyService.save(leaveApply);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SAVA_ERROR, "保存失败");
        }
        return ResultUtils.success(userId);
    }


    /**
     * 查询所有请假申请信息
     *
     * @param leaveApplyQueryDto
     * @param request
     * @return
     */
    @GetMapping("/search")
    public BaseResponse<List<LeaveApply>> searchLeaveApplyInfos(LeaveApplyQueryDto leaveApplyQueryDto, HttpServletRequest request) {

        //仅管理员可看
        if (!AdminUtil.isAdminOrTeacher(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<LeaveApply> queryWrapper = new QueryWrapper<>();

        String uid = leaveApplyQueryDto.getUid();
        String username = leaveApplyQueryDto.getUsername();
        String department = leaveApplyQueryDto.getDepartment();
        String classes = leaveApplyQueryDto.getClasses();
        Integer leaveType = leaveApplyQueryDto.getLeaveType();
        Integer status = leaveApplyQueryDto.getStatus();
        String location = leaveApplyQueryDto.getLocation();
        String address = leaveApplyQueryDto.getAddress();
        String traffic = leaveApplyQueryDto.getTraffic();
        String opinion = leaveApplyQueryDto.getOpinion();
        Date createTime = leaveApplyQueryDto.getCreateTime();


        if (StringUtils.isNotEmpty(uid)) {
            queryWrapper.like("uid", uid);
        }
        if (StringUtils.isNotEmpty(username)) {
            queryWrapper.like("username", username);
        }
        if (StringUtils.isNotEmpty(department)) {
            queryWrapper.eq("department", department);
        }
        if (StringUtils.isNotEmpty(classes)) {
            queryWrapper.eq("classes", classes);
        }
        if (leaveType!=null){
            queryWrapper.eq("leave_type", leaveType);
        }
        if (status!=null){
            queryWrapper.eq("status", status);
        }
        if (StringUtils.isNotEmpty(location)) {
            queryWrapper.eq("location", location);
        }
        if (StringUtils.isNotEmpty(address)) {
            queryWrapper.eq("address", address);
        }
        if (StringUtils.isNotEmpty(classes)) {
            queryWrapper.eq("traffic", traffic);
        }
        if (StringUtils.isNotEmpty(classes)) {
            queryWrapper.eq("opinion", opinion);
        }


        List<LeaveApply> list = leaveApplyService.list(queryWrapper);
        return ResultUtils.success(list);
    }


    @PostMapping("/update")
    public BaseResponse<Integer> updateLeaveApplyInfo(@RequestBody LeaveApply leaveApply, HttpServletRequest request) {
        //1.校验参数是否为空
        if (leaveApply == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2.校验权限 管理员和老师可更新任意用户，普通用户不能修改
        User loginUser = userService.getLoginUser(request);
        //3.触发更新
        int result = leaveApplyService.updateLeaveApplyInfo(leaveApply, loginUser);
        return ResultUtils.success(result);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteLeaveApplyInfo(@RequestBody String id, HttpServletRequest request) {
        if (!AdminUtil.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请假申请信息的编号错误");
        }
        boolean result = leaveApplyService.removeById(id);
        return ResultUtils.success(result);
    }
}
