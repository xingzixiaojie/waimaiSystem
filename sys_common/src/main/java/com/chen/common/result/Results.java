package com.chen.common.result;

/**
 * 业务返回调用
 */
public class Results {

    /**
     * 业务信息
     * @param handler 业务状态
     * @return 业务信息
     */
    public static <T> ResultBO result(ResultStatusHandler handler){
        return result(handler.getCode(), handler.getMessage(), null);
    }

    /**
     * 业务信息
     * @param handler 业务状态
     * @param data 业务数据
     * @return 业务信息
     */
    public static <T> ResultBO result(ResultStatusHandler handler, T data){
        return result(handler).data(data);
    }

    /**
     * 业务信息
     * @param handler 业务状态
     * @param message 业务说明
     * @param data 业务数据
     * @return 业务信息
     */
    public static <T> ResultBO result(ResultStatusHandler handler, String message, T data){
        return result(handler).message(message).data(data);
    }

    /**
     * 业务信息
     * @param code 业务状态码
     * @param message 业务说明
     * @param data 业务数据
     * @return 业务信息
     */
    public static <T> ResultBO result(Integer code, String message, T data){
        return new ResultBO().code(code).message(message).data(data);
    }

}
