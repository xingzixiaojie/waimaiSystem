package com.chen.common.util;

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
	 * 将时间戳转换成字符串
	 * @param timeLong 时间戳毫秒数
	 * @param dateFormat 格式，如：yyyy-MM-dd HH:mm:ss.SSS
	 * @return 时间字符串
	 */
	public static String timeToStr(long timeLong, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Date date = new Date(timeLong);
		return formatter.format(date);
	}

	/**
	 * 将时间戳毫秒数转换为时间字符串格式
	 * @param timestamp 时间戳
	 * @param dateFormat 时间字符串格式，如：yyyy-MM-dd HH:mm:ss.SSS
	 * @return 时间字符串格式
	 */
	public static String timestampToStr(Timestamp timestamp, String dateFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		Date date = new Date(timestamp.getTime());
		return formatter.format(date);
	}

	/**
	 * 计算两个日期相差的秒数
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 相差秒数值
	 */
	public static int getIntvalSecond(Timestamp startTime, Timestamp endTime) {
		//毫秒差
		long intvalMilliseconds = endTime.getTime() - startTime.getTime();
		//秒差
		int intvalSecond = (int) (intvalMilliseconds / 1000);
		return intvalSecond;
	}

	/**
	 * 显示时间格式为友好可读模式
	 * @param showTime 要格式化的显示时间
	 * @return 格式化后的时间字符串
	 */
	public static String showTimeStr(Timestamp showTime){
		//当前时间
		Timestamp curTime = DateUtil.getCurTime();

		//分钟差额
		long curMinute = curTime.getTime() / 1000 / 60;
		long showMinute = showTime.getTime() / 1000 / 60;
		long gapMinute = curMinute - showMinute;

		if(gapMinute <= 1){
			return "刚刚";
		}
		if(gapMinute <= 60){
			return gapMinute + "分钟前";
		}

		//小时差额
		long curHour = curMinute / 60;
		long showHour = showMinute / 60;
		long gapHour = curHour - showHour;

		if(gapHour < 12){
			return gapHour + "小时前";
		}

		return DateUtil.timestampToStr(showTime, "MM-dd HH:mm");
	}

}
