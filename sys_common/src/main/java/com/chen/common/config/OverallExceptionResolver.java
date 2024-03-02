package com.chen.common.config;
// TODO 屏蔽springsecurity影响
//import com.alibaba.fastjson.JSONObject;
//import com.chen.common.asserts.AssertException;
//import com.chen.common.result.ResultStatusEnum;
//import com.chen.common.util.LogUtil;
//import com.google.common.base.Charsets;
//import lombok.SneakyThrows;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.OutputStream;
//
///**
// * 全局异常拦截
// */
//@RestControllerAdvice
//public class OverallExceptionResolver implements HandlerExceptionResolver {
//
//    @Override
//    @SneakyThrows
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//
//        OutputStream out = response.getOutputStream();
//        JSONObject message = new JSONObject();
//        if (ex instanceof AssertException) {
//            AssertException assertException = (AssertException) ex;
//            message.put("code", assertException.getCode());
//            message.put("message", assertException.getMessage());
//        } else if(ex instanceof BadCredentialsException){
//            //SpringSecurity过滤器链中密码对比不一样抛出的异常
//            message.put("code", 401);
//            message.put("message", "用户名或密码错误");
//        } else if (ex instanceof UsernameNotFoundException) {
//            //未登录用户访问受权限保护的页面抛出的异常
//            message.put("code", ResultStatusEnum.SESSION_ERROR.getCode());
//            message.put("message", ex.getMessage());
//        } else if (ex instanceof AccessDeniedException) {
//            //认证过的用户访问无权限资源时会抛出AccessDeniedException异常
//            if(ex.getMessage().equals("此用户无权限访问!")){
//                message.put("code", ResultStatusEnum.NO_POWER.getCode());
//                message.put("message", ex.getMessage());
//            }else {
//                throw ex;
//            }
//        } else if (ex instanceof InternalAuthenticationServiceException) {
//            //UserDetailsServiceImpl中抛出的所有异常,都会被SpringSecurity接管再抛出InternalAuthenticationServiceException
//            message.put("code", 401);
//            message.put("message", ex.getMessage());
//        } else {
//            message.put("code", ResultStatusEnum.SYSTEM_ERROR.getCode());
//            message.put("message", ResultStatusEnum.SYSTEM_ERROR.getMessage());
//
//            LogUtil.printError("【系统出错】\n"
//                    + "接口路径：" + request.getRequestURI() + "\n"
//                    + "异常信息：\n"
//                    + ex.getMessage());
//
//            LogUtil.printException(ex);
//        }
//
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.setContentType("application/json;charset=UTF-8");
//        out.write(message.toJSONString().getBytes(Charsets.UTF_8));
//        return new ModelAndView();
//    }
//
//}
