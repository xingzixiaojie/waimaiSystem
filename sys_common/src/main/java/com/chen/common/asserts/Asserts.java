package com.chen.common.asserts;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务断言，满足条件则返回
 */
public class Asserts {

    /**
     * 对象不为空
     * @param obj 对象
     * @param code 返回码
     * @param message 返回信息
     */
    public static void notNull(Object obj, Integer code, String message) {
        if(obj != null){
            throw new AssertException(code, message);
        }
    }

    /**
     * 对象为空
     * @param obj 对象
     * @param code 返回码
     * @param message 返回信息
     */
    public static void isNull(Object obj, Integer code, String message) {
        if(obj == null){
            throw new AssertException(code, message);
        }
    }

    /**
     * 条件结果是true
     * @param condition 条件结果
     * @param code 返回码
     * @param message 返回信息
     */
    public static void isTrue(boolean condition, Integer code, String message) {
        if(condition){
            throw new AssertException(code, message);
        }
    }

    /**
     * 条件结果是false
     * @param condition 条件结果
     * @param code 返回码
     * @param message 返回信息
     */
    public static void isFalse(boolean condition, Integer code, String message) {
        if(!condition){
            throw new AssertException(code, message);
        }
    }

    /**
     * 是空字符串
     * @param paramStr 字符串
     * @param code 返回码
     * @param message 返回信息
     */
    public static void isEmtpy(String paramStr, Integer code, String message) {
        if(StringUtils.isBlank(paramStr)){
            throw new AssertException(code, message);
        }
    }

}
