package com.chen.common.config;

import com.chen.common.exception.BaseException;
import com.chen.common.result.Result;
import com.chen.common.utils.LogUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
public class OverallExceptionResolver {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        LogUtil.printError("异常信息：{"+ex.getMessage()+"}");
        return Result.error(ex.getMessage());
    }

}

