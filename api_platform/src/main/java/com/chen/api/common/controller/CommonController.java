package com.chen.api.common.controller;

import com.chen.common.constant.MessageConstant;
import com.chen.common.result.Result;
import com.chen.common.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@Api(tags = "3.1 通用接口")
@RestController
@RequestMapping("/admin/common")
public class CommonController {

    /** aliOSSUtil */
    @Resource
    private AliOssUtil aliOssUtil;

    @ApiOperation("3.1.1 文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){

        try {
            //原始文件名
            String originalFileName = file.getOriginalFilename();

            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String objectName = UUID.randomUUID().toString() + extension;
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }

    }

}
