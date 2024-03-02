package com.chen.common.asserts;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 自定义业务断言异常
 */
@Data
@AllArgsConstructor
public class AssertException extends RuntimeException {

    /** 业务状态码 */
    private Integer code;

    /** 业务说明 */
    private String message;

    @Override
    public String toString() {
        return "Result{code=" + code + ",message='" + message + "'}";
    }

}
