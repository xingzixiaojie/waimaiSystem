package com.chen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc  //springMVC
@Configuration  //启用配置模式
@EnableScheduling  //定时任务
@MapperScan("com.chen.core.*.mapper")  //扫描mapper文件包
@SpringBootApplication(scanBasePackages = {"com.chen"})
public class Platform {

    public static void main(String[] args) {
        SpringApplication.run(Platform.class, args);
    }

}
