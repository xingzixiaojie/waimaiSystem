package com.chen.common.config;

import com.chen.common.properties.AliOssProperties;
import com.chen.common.utils.AliOssUtil;
import com.chen.common.utils.LogUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){

        LogUtil.printInfo("开始创建阿里云文件上传工具类对象：{"+aliOssProperties+"}");
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }

}
