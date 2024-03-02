package com.chen.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.xld.common.result.ResultBO;
import com.xld.common.result.ResultStatusEnum;
import com.xld.common.result.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 所有的接口路径
 */
@Api(tags = "0 接口API")
@ApiSort(value = 0)
@RestController
public class UrlsController {

    @Resource
    WebApplicationContext applicationContext;

    @ApiOperation("获取所有接口")
    @GetMapping("/getAllUrl")
    public ResultBO getAllUrl() {
        String[] names = applicationContext.getBeanNamesForType(RequestMappingHandlerMapping.class);
        List<Map<String, String>> list = new ArrayList<>();
        for (String name : names) {
            RequestMappingHandlerMapping mapping = applicationContext.getBean(name, RequestMappingHandlerMapping.class);
            Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

            for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
                Map<String, String> map1 = new HashMap<>();
                RequestMappingInfo info = m.getKey();
                HandlerMethod method = m.getValue();
                PatternsRequestCondition p = info.getPatternsCondition();
                map1.put("url", p.getPatterns().stream().collect(Collectors.joining()));
                map1.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
                map1.put("method", method.getMethod().getName()); // 方法名
                RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
                for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                    map1.put("type", requestMethod.toString());
                }

                list.add(map1);
            }
        }

        JSONObject data = new JSONObject();
        data.put("ListVO", list);
        return Results.result(ResultStatusEnum.SUCCESS, data);
    }
}
