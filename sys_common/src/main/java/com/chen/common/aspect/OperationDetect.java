package com.chen.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class OperationDetect {

    //TODO OperationlogMapper的Insert方法还未编写
//    @Autowired
//    OperationlogMapper operationlogMapper;
//
//    @Around("@annotation(com.chen.common.annotation.OperationLog)")
//    public Object operationDetect(ProceedingJoinPoint joinPoint) throws Throwable {
//        Long userId = BaseContext.getCurrentId();
//        String exceptionDetected=null;
//        Object proceed=null;
//        LocalDateTime updateTime = LocalDateTime.now();
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        String url = request.getRequestURL().toString();
//        String[] split = url.split("/");
//        int portCheck = split[3].equals("admin")?1:2;
//        HttpServletRequest request1 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        // 获取请求用户IP
//
//        String ip = IPAddressUtils.getIpAdrress(request1);
//        if (ip == null) {
//            return "error/limit";
//        }
//        String method = joinPoint.getSignature().getName();
//        String args = JSONObject.toJSONString(joinPoint.getArgs());
//        try {
//            proceed = joinPoint.proceed();
//        } catch (Throwable ex) {
//            exceptionDetected = ex.getMessage();
//            ex.printStackTrace();
//        }finally {
//            operationlogMapper.insert(url,portCheck,ip,method,exceptionDetected,args,userId,updateTime);
//        }
//        return proceed;
//    }

}
