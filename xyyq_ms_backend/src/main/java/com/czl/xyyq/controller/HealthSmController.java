package com.czl.xyyq.controller;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czl.xyyq.common.BaseResponse;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.common.ResultUtils;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.model.dto.HealthSmQueryDto;
import com.czl.xyyq.model.entity.HealthSm;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.model.request.UserLoginRequest;
import com.czl.xyyq.model.request.UserRegisterRequest;
import com.czl.xyyq.service.HealthSmService;
import com.czl.xyyq.service.UserService;
import com.czl.xyyq.utils.AdminUtil;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.czl.xyyq.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 健康接口
 *
 * @author caizhenlong.
 * @create 2023/2/23
 */
@RestController
@RequestMapping("/health")
public class HealthSmController {

    @Resource
    private HealthSmService healthSmService;

    @Resource
    UserService userService;

    /**
     * 添加健康上报信息
     *
     * @param healthSm
     * @param request
     * @return
     */
    @PostMapping("/addHealthInfo")
    public BaseResponse<Integer> addHealthInfo(@RequestBody HealthSm healthSm, HttpServletRequest request) {
        if (healthSm == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //获取登录的用户,作为填写者
        User loginUser = userService.getLoginUser(request);
        String userId = loginUser.getId();
        String username = loginUser.getUsername();
        healthSm.setUid(userId);
        healthSm.setUsername(username);

        Integer atSchool = healthSm.getAtSchool();
        Integer physicalCondition = healthSm.getPhysicalCondition();
        Integer isHighRisk = healthSm.getIsHighRisk();
        Integer healthCodeStatus = healthSm.getHealthCodeStatus();

        if (!StringUtils.isNotBlank(userId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (physicalCondition < 0 && isHighRisk < 0 && healthCodeStatus < 0 && atSchool<0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean saveResult = healthSmService.save(healthSm);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SAVA_ERROR, "保存失败");
        }
        Integer result = healthSm.getId();
        return ResultUtils.success(result);
    }


    /**
     * 查询所有健康上报信息
     * @param healthSmQueryDto
     * @param request
     * @return
     */
    @GetMapping("/search")
    public BaseResponse<List<HealthSm>> searchHealths(HealthSmQueryDto healthSmQueryDto, HttpServletRequest request) {

        //教师和管理员可看
        if (!AdminUtil.isAdminOrTeacher(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<HealthSm> queryWrapper = new QueryWrapper<>();

        String uid = healthSmQueryDto.getUid();
        String username = healthSmQueryDto.getUsername();
        Integer atSchool = healthSmQueryDto.getAtSchool();
        Integer physicalCondition = healthSmQueryDto.getPhysicalCondition();
        String location = healthSmQueryDto.getLocation();
        Integer isHighRisk = healthSmQueryDto.getIsHighRisk();
        Integer healthCodeStatus = healthSmQueryDto.getHealthCodeStatus();

        if (StringUtils.isNotEmpty(uid)) {
            queryWrapper.like("uid", uid);
        }
        if (StringUtils.isNotEmpty(username)) {
            queryWrapper.like("username", username);
        }
        if (StringUtils.isNotEmpty(location)) {
            queryWrapper.like("location", location);
        }
        if (atSchool != null) {
            queryWrapper.eq("at_school", atSchool);
        }
        if (physicalCondition != null) {
            queryWrapper.eq("physical_condition", physicalCondition);
        }
        if (isHighRisk != null) {
            queryWrapper.eq("is_high_risk", isHighRisk);
        }
        if (healthCodeStatus != null) {
            queryWrapper.eq("health_code_status", healthCodeStatus);
        }

        List<HealthSm> list = healthSmService.list(queryWrapper);
        return ResultUtils.success(list);
    }


    @PostMapping("/update")
    public BaseResponse<Integer> updateHealthSm(@RequestBody HealthSm healthSm,HttpServletRequest request){
        //1.校验参数是否为空
        if (healthSm==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2.校验权限 管理员和教师可更新任意用户，普通用户不能修改
        User loginUser = userService.getLoginUser(request);
        //3.触发更新
        int result = healthSmService.updateHealthSm(healthSm,loginUser);
        return ResultUtils.success(result);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteHealthSm(@RequestBody Integer id,HttpServletRequest request) {
        if (!AdminUtil.isAdminOrTeacher(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id==null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"学号错误");
        }
        boolean result = healthSmService.removeById(id);
        return ResultUtils.success(result);

    }
}
