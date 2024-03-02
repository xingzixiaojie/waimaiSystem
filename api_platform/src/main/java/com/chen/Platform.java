package com.chen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc  //springMVC
@Configuration  //启用配置模式
@EnableScheduling  //定时任务
//@MapperScan("com.chen.core.*.mapper")  //扫描mapper文件包
@SpringBootApplication(scanBasePackages = {"com.chen"})
public class Platform {

    public static void main(String[] args) {
        SpringApplication.run(Platform.class, args);
    }

    /** 跨域配置 */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addExposedHeader("3600");
        return corsConfiguration;
    }

    /** 跨域过滤器 */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

}
