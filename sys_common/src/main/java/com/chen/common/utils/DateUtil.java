package com.chen.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期/时间 转换
 */
public class DateUtil {
	
	/**
	 * 获取当前时间
	 * 推荐此种方法，执行速度快
	 * @return 时间戳
	 */
	public static Timestamp getCurTime(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 将时间字符串转换成时间戳
	 * @param timeStr 时间字符串
	 * @param dateFormat 格式，如：yyyy-MM-dd HH:mm:ss.SSS
	 * @return 时间戳
	 */
	public static Timestamp strToTime(String timeStr, String dateFormat){
		if (StringUtils.isBlank(timeStr)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			Date date = sdf.parse(timeStr);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			LogUtil.printException(e);
		}
		return null;
	}

	/**
	 * 将时间戳毫秒数转换为时间字符串格式
	 * @param timestamp 时间戳
	 * @param dateFormat 时间字符串格式，如：yyyy-MM-dd HH:mm:ss.SSS
	 * @return 时间字符串格式
	 */
	public static String timeToStr(Timestamp timestamp, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		Date date = new Date(timestamp.getTime());
		return formatter.format(date);
	}

}
