package com.chen.common.util;


import com.chen.common.result.ResultBO;
import org.apache.commons.lang3.StringUtils;

/**
 * 账号/密码效验工具
 */
public class CheckUtil {

	/**
	 * 账号规则效验
	 * <br> 账号长度须6到16位，只支持小写字母、大写字母、数字
	 * @param account 账号
	 * @return code=1：验证通过，code!=1：验证失败
	 */
	public static ResultBO checkAccount(String account) {
		ResultBO resultBO = new ResultBO();

		if(StringUtils.isBlank(account)){
			resultBO.setCode(2);
			resultBO.setMessage("账号不能为空");
			return resultBO;
		}

		if(account.length() < 6 || account.length() > 16){
			resultBO.setCode(3);
			resultBO.setMessage("账号长度不符，须6到16位，只支持小写字母、大写字母、数字");
			return resultBO;
		}

		boolean isFalse = false;
		// 检查密码字符种类
		for (char c : account.toCharArray()) {
			if (Character.isLowerCase(c) || Character.isUpperCase(c) || Character.isDigit(c)) {
				continue;
			}
			//非法字符
			isFalse = true;
			break;
		}

		if(isFalse){
			resultBO.setCode(4);
			resultBO.setMessage("账号长度须6到16位，只支持小写字母、大写字母、数字");
			return resultBO;
		}

		resultBO.setCode(1);
		resultBO.setMessage("验证通过");
		return resultBO;
	}

	/**
	 * 密码强度效验
	 * <br> 密码长度须6到16位，至少包含小写字母、大写字母、数字、特殊字符中的两种
	 * @param password 原密码
	 * @return code=1：验证通过，code!=1：验证失败
	 */
	public static ResultBO checkPassword(String password) {
		ResultBO resultBO = new ResultBO();

		if(StringUtils.isBlank(password)){
			resultBO.setCode(2);
			resultBO.setMessage("密码不能为空");
			return resultBO;
		}

		if(password.length() < 6 || password.length() > 16){
			resultBO.setCode(3);
			resultBO.setMessage("密码长度不符，须6到16位，至少包含小写字母、大写字母、数字、特殊字符中的两种");
			return resultBO;
		}

		boolean hasLowerCase = false;  //小写字母
		boolean hasUpperCase = false;  //大写字母
		boolean hasDigit = false;  //数字
		boolean hasSpecialChar = false;  //特殊字符

		// 检查密码字符种类
		for (char c : password.toCharArray()) {
			if (Character.isLowerCase(c)) {
				hasLowerCase = true;
			} else if (Character.isUpperCase(c)) {
				hasUpperCase = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			} else {
				hasSpecialChar = true;
			}
		}

		int hasNUm = 0;
		if(hasLowerCase) hasNUm ++;
		if(hasUpperCase) hasNUm ++;
		if(hasDigit) hasNUm ++;
		if(hasSpecialChar) hasNUm ++;

		if(hasNUm <= 1){
			resultBO.setCode(4);
			resultBO.setMessage("密码太弱，至少需要包含小写字母、大写字母、数字、特殊字符中的两种");
			return resultBO;
		}

		resultBO.setCode(1);
		resultBO.setMessage("验证通过");
		return resultBO;
	}

}
