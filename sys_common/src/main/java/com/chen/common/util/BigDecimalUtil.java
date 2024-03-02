package com.chen.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数据精确处理工具类（用于小数点情况提供精确计算）
 * @author GQL
 *
 */
public class BigDecimalUtil {

    /**
     * 小数点最后一位的处理方式
     * <br> DOWN：向下取整
     * <br> HALF_UP：四舍五入
     */
    private static final RoundingMode roundingMode = RoundingMode.HALF_UP;

    /**
     * 加法（向下取整）
     * @param value1 被加数
     * @param value2 加数
     * @param scale 保留小数位
     * @return 两个参数的和
     */
    public static double add(double value1, double value2, int scale){
        BigDecimal b1 = new BigDecimal(value1 + "");
        BigDecimal b2 = new BigDecimal(value2 + "");
        return b1.add(b2).setScale(scale, roundingMode).doubleValue();
    }

    /**
     * 减法（向下取整）
     * @param value1 被减数
     * @param value2 减数
     * @param scale 保留小数位
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2, int scale){
        BigDecimal b1 = new BigDecimal(value1 + "");
        BigDecimal b2 = new BigDecimal(value2 + "");
        return b1.subtract(b2).setScale(scale, roundingMode).doubleValue();
    }

    /**
     * 乘法（向下取整）
     * @param value1 被乘数
     * @param value2 乘数
     * @param scale 保留小数位
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2, int scale){
        BigDecimal b1 = new BigDecimal(value1 + "");
        BigDecimal b2 = new BigDecimal(value2 + "");
        return b1.multiply(b2).setScale(scale, roundingMode).doubleValue();
    }

    /**
     * 除法（向下取整）
     * @param value1 被除数
     * @param value2 除数
     * @param scale 保留小数位数
     * @return 两个参数的商
     */
    public static double div(double value1, double value2, int scale){
        if(value2 == 0){
            return 0;
        }

        BigDecimal b1 = new BigDecimal(value1 + "");
        BigDecimal b2 = new BigDecimal(value2 + "");
        return b1.divide(b2, scale, roundingMode).doubleValue();
    }

    /**
     * double型输出整数不显示小数点和其后的0，最多显示2位小数
     * @param d 要处理的浮点数
     * @return 处理后的字符串
     */
    public static String doubleTrans(double d){
        double handleDouble = BigDecimalUtil.add(d, 0, 2);

        if(Math.round(handleDouble)-handleDouble == 0){
            return String.valueOf((long)handleDouble);
        }
        return String.valueOf(handleDouble);
    }

    /**
     * 判断double是否是整数
     * @param obj 要处理的浮点数
     * @return 是整数：返回true，不是整数：返回false
     */
    public static boolean isIntegerForDouble(double obj) {
        double eps = 1e-10;  // 精度范围
        return obj-Math.floor(obj) < eps;
    }

}
