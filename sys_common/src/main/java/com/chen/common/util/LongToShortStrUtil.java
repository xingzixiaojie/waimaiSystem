package com.chen.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Long的Id与短字符串(57进制)互相转换
 */
public class LongToShortStrUtil {

	/** 初始化57进制数据，索引位置代表字符的数值，去除：大写I、小写i、小写l、大写O、小写o、小写z */
	private static final String chars = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxy";
	private static final int scale = 57;

	/**
	 * 10进制转57进制（只处理正数）
	 * @param num long值参数，最大值：9223372036854775807
	 * @return 57进制字符串，转换失败：返回null
	 */
	public static String to57Str(Long num){
		if(num < 0){
			return null;
		}

		StringBuilder sb = new StringBuilder();
		int remainder;
		while (num > scale - 1) {
			remainder = Long.valueOf(num % scale).intValue();
			sb.append(chars.charAt(remainder));
			num = num / scale;
		}
		sb.append(chars.charAt(num.intValue()));
		return sb.reverse().toString();
	}

	/**
	 * 57进制转10进制（只处理正数）
	 * @param str 57进制字符串，最大字符串：AzL8n0Y58m7
	 * @return 10进制数值，转换失败：返回null
	 */
	public static Long to10Long(String str){
		if(StringUtils.isBlank(str)){
			return null;
		}

		//Long的最大值转换出来的结果是：AzL8n0Y58m7
		if(str.length() > 11){
			return null;
		}

		long num = 0;
		int index;
		for (int i = 0; i < str.length(); i++) {
			index = chars.indexOf(str.charAt(i));
			if(index < 0){
				//非57个字符中的字符
				return null;
			}
			num += (long) (index * (Math.pow(scale, str.length() - i - 1)));
		}

		if(num >= 0){
			return num;
		}else{
			return null;
		}
	}

}
