package com.chen.common.result;

/**
 * 业务及网络状态编码
 */
public interface ResultStatusHandler {

    /** 业务状态码 */
    Integer getCode();

    /** 业务说明 */
    String getMessage();
}
