package com.czl.xyyq.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author caizhenlong
 * @create 2022-11-23 17:32
 */
public interface OssService {
    //上传头像
    String uploadFile(MultipartFile file);
}
