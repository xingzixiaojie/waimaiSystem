package com.chen.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 */
public class LogUtil {

	/**
	 * 异常信息打印（日志配置的自定义system_error类型）
	 * @param ex 异常信息
	 */
	public static void printException(Exception ex){
		Logger logger  =  LoggerFactory.getLogger("my_error");

		StringBuilder logOut = new StringBuilder();
		logOut.append("\n");
		logOut.append(ex.toString());
		logOut.append("\n");
		
		StackTraceElement[] errorList = ex.getStackTrace();
		for (StackTraceElement stackTraceElement : errorList) {
			logOut.append(stackTraceElement.toString());
			logOut.append("\n");
		}

		logger.error(logOut.toString());
	}

	/**
	 * 错误信息打印（日志配置的自定义system_error类型）
	 * @param errorStr 错误信息文本
	 */
	public static void printError(String errorStr){
		Logger logger  =  LoggerFactory.getLogger("my_error");
		String logStr = "\n" + errorStr + "\n";
		logger.error(logStr);
	}

	/**
	 * 业务信息打印（日志配置的自定义my_info类型）
	 * @param infoStr 信息文本
	 */
	public static void printInfo(String infoStr){
		Logger logger = LoggerFactory.getLogger("my_info");
		String logStr = "\n" + infoStr + "\n";
		logger.info(logStr);
	}

}
