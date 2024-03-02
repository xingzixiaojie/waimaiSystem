package com.chen.common.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.xld.common.result.ResultStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
public class Swagger2Configuration {

    /** 运行时环境 */
    @Autowired
    private Environment env;

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        ResponseMessage unauthorized = new ResponseMessage(ResultStatusEnum.SESSION_ERROR.getCode(), "未登录或登录过期",
                null, Collections.emptyMap(), Collections.emptyList());
        List<ResponseMessage> rmList = new ArrayList<>();
        rmList.add(unauthorized);


        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("token").description("登录后的会话标识")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

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

    /**
     * api文档的详细信息函数,注意这里的注解引用的是哪个
     */
    private ApiInfo apiInfo() {
        String title = env.getProperty("myself.swagger2.title");

        return new ApiInfoBuilder()
                .title(title)  //大标题
                .description("接口文档地址：https://api.xliandong.com/XXX/XXX/doc.html")  //描述
                .termsOfServiceUrl("https://api.xliandong.com/xxx/xxx/")
                .build();
    }

}
