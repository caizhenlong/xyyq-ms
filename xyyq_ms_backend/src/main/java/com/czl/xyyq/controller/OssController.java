package com.czl.xyyq.controller;

import com.czl.xyyq.common.BaseResponse;
import com.czl.xyyq.common.ResultUtils;
import com.czl.xyyq.service.OssService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/oss/file")
public class OssController {

    @Resource
    private OssService ossService;

    //上传图片
    @PostMapping("/upload")
    public BaseResponse<String> uploadOssFile(@RequestParam("file") MultipartFile multipartFile){
        String url = ossService.uploadFile(multipartFile);
        return ResultUtils.success(url);
    }
}