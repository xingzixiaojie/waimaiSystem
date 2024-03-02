package com.chen.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Random;

/**
 * 随机编号生成工具类
 */
public class NoUtil implements Serializable {

    /**
     * 创建纯数字的订单号
     * @param orderNoPrefix 订单编号前缀
     * @return 订单号，格式：业务码（2位）+年月日（6位）+随机数（12位）
     */
    public static String createOrderNo(String orderNoPrefix) {
        if(StringUtils.isBlank(orderNoPrefix)){
            return null;
        }

        String dateStr = DateUtil.timeToStr(DateUtil.getCurTime().getTime(), "yyMMdd");
        String orderNo = orderNoPrefix + dateStr + createDigitCode(12);
        return orderNo;
    }

    /**
     * 创建字母和数字的随机组合字符串
     * @param digitNumber 需要的字符串长度
     * @return 字符串
     */
    public static String createInvitationCode(int digitNumber) {
        StringBuilder invitationCode = new StringBuilder();
        Random random = new Random();
        String[] seed = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "3", "4", "5", "6", "7", "8", "9"
        };
        for (int i = 0; i < digitNumber; i++) {
            int index = random.nextInt(seed.length);
            invitationCode.append(seed[index]);
        }
        return invitationCode.toString();
    }

    /**
     * 创建纯数字的字符串
     * @param digitNumber 需要的字符串长度
     * @return 字符串
     */
    public static String createDigitCode(int digitNumber) {
        // 生成随机类
        Random random = new Random();
        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < digitNumber; i++) {
            sRand.append(random.nextInt(10));
        }
        return sRand.toString();
    }

}
