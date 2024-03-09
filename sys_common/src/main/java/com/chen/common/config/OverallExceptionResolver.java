package com.chen.common.config;

import com.alibaba.fastjson.JSONObject;
import com.chen.common.asserts.AssertException;
import com.chen.common.result.ResultStatusEnum;
import com.chen.common.util.LogUtil;
import com.google.common.base.Charsets;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@RestControllerAdvice
public class OverallExceptionResolver implements HandlerExceptionResolver {

    @Override
    @SneakyThrows
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        OutputStream out = response.getOutputStream();
        JSONObject message = new JSONObject();
        if (ex instanceof AssertException) {
            AssertException assertException = (AssertException) ex;
            message.put("code", assertException.getCode());
            message.put("message", assertException.getMessage());
        } else {
            message.put("code", ResultStatusEnum.SYSTEM_ERROR.getCode());
            message.put("message", ResultStatusEnum.SYSTEM_ERROR.getMessage());

            LogUtil.printError("【系统出错】\n"
                    + "接口路径：" + request.getRequestURI() + "\n"
                    + "异常信息：\n"
                    + ex.getMessage());

            LogUtil.printException(ex);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        out.write(message.toJSONString().getBytes(Charsets.UTF_8));
        return new ModelAndView();
    }

}

