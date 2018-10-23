package com.util;

import com.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtils {

	@Autowired
	HttpServletRequest request;


	/**
	 * 验证是否为手机号
	 *
	 * @param phone
	 *
	 */
	public static void checkPhone(String phone) {
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		if (phone.length() != 11) {
			throw new InvalidRequestException();
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(phone);
		if (!m.matches()) {
			throw new InvalidRequestException();
		}
	}

	/**
	 * 验证是否为email
	 *
	 * @param email
	 *
	 */
	public static void checkEmail(String email) {
		String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if(!m.matches()){
			throw new InvalidRequestException();
		}
	}

	/**
	 * 验证密码是否正确 密码要求：8到20位数字或者字母或者下划线组成，不能纯数字或者字母，可以不包括下划线
	 *
	 * @param password
	 *
	 */
	public static void checkPassword(String password) {
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,20}$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(password);
		if(!m.matches()){
			throw new InvalidRequestException();
		}
	}

	/**
	 * 验证真实姓名格式 要求：2-20位汉字中文名/2-20位字母外文名，可以包括空格或者 ·
	 *
	 * */
	public static void checkRealName(String realName){
		String regex = "(^[\\u4E00-\\u9FA5]{2,20}$)|(^[A-Za-z]{1}[A-Za-z\\s]{1,19}$)";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(realName);
		if(!m.matches()){
			throw new InvalidRequestException();
		}
	}

	/**
	 * 验证用户名格式 要求：6-8位数字大小写字母或者下划线
	 *
	 * */
	public static void checkUsername(String username){
		String regex = "(^[\\w\\u4e00-\\u9fa5]{6,8}$)";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(username);
		if(!m.matches()){
			throw new InvalidRequestException();
		}
	}

	/**
	 * 验证字符串是否为空
	 *
	 */
	public static boolean isNullOrEmpty(String string) {
		if (string == null || "".equals(string)) {
			return true;
		}
		return false;
	}

	/**
	 * MD5加密
	 *
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

}
