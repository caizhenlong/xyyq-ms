package com.czl.xyyq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czl.xyyq.common.BaseResponse;
import com.czl.xyyq.common.ErrorCode;
import com.czl.xyyq.common.ResultUtils;
import com.czl.xyyq.exception.BusinessException;
import com.czl.xyyq.model.dto.MaterialInfoQueryDto;
import com.czl.xyyq.model.dto.MaterialOutQueryDto;
import com.czl.xyyq.model.entity.MaterialInfo;
import com.czl.xyyq.model.entity.MaterialOut;
import com.czl.xyyq.model.entity.User;
import com.czl.xyyq.service.MaterialInfoService;
import com.czl.xyyq.service.MaterialOutService;
import com.czl.xyyq.service.UserService;
import com.czl.xyyq.utils.AdminUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 物资管理接口
 *
 * @author caizhenlong.
 * @create 2023/2/23
 */
@RestController
@RequestMapping("/material")
public class MaterialInfoController {

    @Resource
    private MaterialInfoService materialInfoService;

    @Resource
    private MaterialOutService materialOutService;

    @Resource
    UserService userService;

    /**
     * 添加物资信息
     *
     * @param materialInfo
     * @param request
     * @return
     */
    @PostMapping("/addMaterialInfo")
    public BaseResponse<Integer> addMaterialInfo(@RequestBody MaterialInfo materialInfo, HttpServletRequest request) {
        if (materialInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //获取登录的用户,作为填写者
        User loginUser = userService.getLoginUser(request);
        String username = loginUser.getUsername();
        materialInfo.setCreator(username);

        String typeName = materialInfo.getTypeName();
        String name = materialInfo.getName();
        String img = materialInfo.getImg();
        String specification = materialInfo.getSpecification();
        String unit = materialInfo.getUnit();
        Integer total = materialInfo.getTotal();
        Integer status = materialInfo.getStatus();

        if (StringUtils.isAnyBlank(typeName, name, img, specification, unit)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (total == null && total < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (status == null && status < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean saveResult = materialInfoService.save(materialInfo);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SAVA_ERROR, "保存失败");
        }
        Integer result = materialInfo.getId();
        return ResultUtils.success(result);
    }


    /**
     * 查询所有物资信息
     *
     * @param materialInfoQueryDto
     * @param request
     * @return
     */
    @GetMapping("/search")
    public BaseResponse<List<MaterialInfo>> searchMaterialInfos(MaterialInfoQueryDto materialInfoQueryDto, HttpServletRequest request) {

        //仅管理员可看
        if (!AdminUtil.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<MaterialInfo> queryWrapper = new QueryWrapper<>();

        String name = materialInfoQueryDto.getName();
        String creator = materialInfoQueryDto.getCreator();
        String typeName = materialInfoQueryDto.getTypeName();

        if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotEmpty(creator)) {
            queryWrapper.like("creator", creator);
        }
        if (StringUtils.isNotEmpty(typeName)) {
            queryWrapper.like("type_name", typeName);
        }

        List<MaterialInfo> list = materialInfoService.list(queryWrapper);
        return ResultUtils.success(list);
    }


    @PostMapping("/update")
    public BaseResponse<Integer> updateMaterialInfo(@RequestBody MaterialInfo materialInfo, HttpServletRequest request) {
        //1.校验参数是否为空
        if (materialInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2.校验权限 管理员可更新任意用户，普通用户不能修改
        User loginUser = userService.getLoginUser(request);
        //3.触发更新
        int result = materialInfoService.updateMaterialInfo(materialInfo, loginUser);
        return ResultUtils.success(result);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteMaterialInfo(@RequestBody Integer id, HttpServletRequest request) {
        if (!AdminUtil.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "物资编号错误");
        }
        boolean result = materialInfoService.removeById(id);
        return ResultUtils.success(result);
    }


    //物资出库
    @Transactional
    @PostMapping("/materialOut")
    public BaseResponse<Boolean> materialOut(@RequestBody MaterialOut materialOut, HttpServletRequest request) {

        //传递物资id/名称 出库数量 出库负责人 出库原因

        if (!AdminUtil.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (materialOut == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String name = materialOut.getName();
        Integer quantity = materialOut.getQuantity();
        String superintendent = materialOut.getSuperintendent();
        String reason = materialOut.getReason();

        if (StringUtils.isAnyBlank(name, superintendent, reason)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (quantity <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //更新库存之前先查询库存是否足够被取出
        MaterialInfo materialInfo = materialInfoService.getOne(new QueryWrapper<MaterialInfo>().eq("name", name));
        if(materialInfo==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        }
        if (materialInfo.getStatus()!=1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"物资不可用");
        }

        Integer oldTotal = materialInfo.getTotal();
        // 足够取出就进行出库，将出库信息进行登记
        if (oldTotal >= quantity) {
            materialInfoService.updateTotal(materialOut);
        }else{
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"库存不足,剩余库存："+oldTotal);
        }

        boolean saveResult = materialOutService.save(materialOut);
        return ResultUtils.success(true);
    }


    /**
     * 查询所有物资出库信息
     *
     * * @param request
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<MaterialOut>> searchMaterialOutInfo(MaterialOutQueryDto materialOutQueryDto,HttpServletRequest request) {

        //仅管理员可看
        if (!AdminUtil.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }

        QueryWrapper<MaterialOut> queryWrapper = new QueryWrapper<>();

        String name = materialOutQueryDto.getName();
        Integer quantity = materialOutQueryDto.getQuantity();
        String superintendent = materialOutQueryDto.getSuperintendent();

        if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (quantity!=null) {
            queryWrapper.eq("quantity", quantity);
        }
        if (StringUtils.isNotEmpty(superintendent)) {
            queryWrapper.like("superintendent", superintendent);
        }

        List<MaterialOut> list = materialOutService.list(queryWrapper);
        return ResultUtils.success(list);
    }


}
