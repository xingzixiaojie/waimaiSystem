package com.chen.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具
 */
public class StringUtil {

    /**
     * 字符串格式化
     * <br> 去除字符中的\r\n\t字符，但不删除正常的空格
     * <br> 不能用正则表达式，会删除正常的空格
     * <br> \"格式化成单引号
     * @return 处理后的字符串
     */
    public static String format(String paramStr) {
        if(StringUtils.isBlank(paramStr)){
            return "";
        }

        paramStr = paramStr.replace("\"", "'");
        paramStr = paramStr.replace("\n", "");
        paramStr = paramStr.replace("\r", "");
        paramStr = paramStr.replace("\t", "");
        return paramStr;
    }

    /**
     * 字符串对比
     * <br>null与空串对比结果为相等
     * @param aStr 字符串
     * @param bStr 字符串
     * @return 相同：返回true，不相同返回false
     */
    public static boolean equals(String aStr, String bStr) {
        if(StringUtils.isBlank(aStr) && StringUtils.isBlank(bStr)){
            return true;
        }
        if(StringUtils.isBlank(aStr) && StringUtils.isNotBlank(bStr)){
            return false;
        }
        if(StringUtils.isNotBlank(aStr) && StringUtils.isBlank(bStr)){
            return false;
        }
        return aStr.equals(bStr);
    }

}
