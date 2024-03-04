package com.chen.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * MD5操作
 * @author xld
 *
 */
public class MD5Util {
	
	/**
	 * 执行MD5加密
	 * @param paramStr 原字符串
	 * @return 加密后的字符串
	 */
	public static String toMd5(String paramStr){
		if(paramStr == null || paramStr.isEmpty()){
			return null;
		}

		try {
			byte[] btInput = paramStr.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得散列后的值
			byte[] md = mdInst.digest();

			String md5Str1 = getMD5Str(md);
			//抛掉前2位和后2位
			md5Str1 = md5Str1.substring(2, 30);

			//第二次散列
			byte[] btInput2 = md5Str1.getBytes();
			MessageDigest mdInst2 = MessageDigest.getInstance("MD5");
			mdInst2.update(btInput2);
			byte[] md2 = mdInst2.digest();

			return getMD5Str(md2);
		} catch (Exception e) {
			LogUtil.printException(e);
			return null;
		}
	}
	
	/**
	 * 将MD5散列后的值转化成字符串
	 * @param paramByte MD5散列后的数组
	 * @return 转化后的字符串
	 */
	private static String getMD5Str(byte[] paramByte){
		StringBuilder md5StrBuff = new StringBuilder();
		for (byte aParamByte : paramByte) {
			if (Integer.toHexString(0xFF & aParamByte).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & aParamByte));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & aParamByte));
			}

		}
		return md5StrBuff.toString();
	}
	
	/**
	 * 简单的md5散列
	 * @param paramStr 原字符串
	 * @return 转化后的字符串
	 */
	public static String md5Str(String paramStr){
		if(paramStr == null || paramStr.isEmpty()){
			return null;
		}

		String resultStr = "";
		
		try {
            byte[] btInput = paramStr.getBytes(StandardCharsets.UTF_8);
			// 获得MD5摘要算法的 MessageDigest 对象  
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得散列后的值
			byte[] md = mdInst.digest();
			
			resultStr = getMD5Str(md);
        } catch (Exception e) {
			LogUtil.printException(e);
		}

        return resultStr;
	}
	
}
