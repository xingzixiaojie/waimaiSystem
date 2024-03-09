package com.chen.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.List;

/**
 * SpringMVC配置
 */
@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

        resolvers.add(new OverallExceptionResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        //本地文件保存
        File file = new File("upload");
        String uploadFilePath = file.getAbsolutePath() + "/";
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + uploadFilePath);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
