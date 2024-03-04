package com.chen.common.config;

import com.chen.common.result.ResultStatusEnum;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * swagger配置
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        List<ResponseMessage> rmList = new ArrayList<>();
        ResponseMessage system_error = new ResponseMessage(ResultStatusEnum.SYSTEM_ERROR.getCode(), "系统错误",
                null, Collections.emptyMap(), Collections.emptyList());
        rmList.add(system_error);
        ResponseMessage no_power = new ResponseMessage(ResultStatusEnum.NO_POWER.getCode(), "无访问权限",
                null, Collections.emptyMap(), Collections.emptyList());
        rmList.add(no_power);
        ResponseMessage success = new ResponseMessage(ResultStatusEnum.SUCCESS.getCode(), "操作成功",
                null, Collections.emptyMap(), Collections.emptyList());
        rmList.add(success);
        ResponseMessage fail = new ResponseMessage(ResultStatusEnum.FAIL.getCode(), "操作失败",
                null, Collections.emptyMap(), Collections.emptyList());
        rmList.add(fail);
        ResponseMessage token_error = new ResponseMessage(ResultStatusEnum.TOKEN_ERROR.getCode(), "未登录或登录过期",
                null, Collections.emptyMap(), Collections.emptyList());
        rmList.add(token_error);
        ResponseMessage param_error = new ResponseMessage(ResultStatusEnum.PARAM_ERROR.getCode(), "参数错误",
                null, Collections.emptyMap(), Collections.emptyList());
        rmList.add(param_error);
        ResponseMessage business_error = new ResponseMessage(400, "401~499业务错误",
                null, Collections.emptyMap(), Collections.emptyList());
        rmList.add(business_error);

        //自定义参数
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token").description("登录后的会话标识")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.GET, rmList)
                .globalResponseMessage(RequestMethod.POST, rmList)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xld")) //扫描包路径
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    /** api文档的详细配置信息 */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("管理平台-API文档").build();
    }

}
