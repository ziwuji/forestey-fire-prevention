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
	 * ��֤�Ƿ�Ϊ�ֻ���
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
	 * ��֤�Ƿ�Ϊemail
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
	 * ��֤�����Ƿ���ȷ ����Ҫ��8��20λ���ֻ�����ĸ�����»�����ɣ����ܴ����ֻ�����ĸ�����Բ������»���
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
	 * ��֤��ʵ������ʽ Ҫ��2-20λ����������/2-20λ��ĸ�����������԰����ո���� ��
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
	 * ��֤�û�����ʽ Ҫ��6-8λ���ִ�Сд��ĸ�����»���
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
	 * ��֤�ַ����Ƿ�Ϊ��
	 *
	 */
	public static boolean isNullOrEmpty(String string) {
		if (string == null || "".equals(string)) {
			return true;
		}
		return false;
	}

	/**
	 * MD5����
	 *
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("û��md5����㷨��");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16��������
		// �����������δ��32λ����Ҫǰ�油0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

}
