package com.util;

import java.util.Random;

public class MathUtils {

	final static String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了

	public static String randomNumStr(int size) {
		StringBuffer flag = new StringBuffer();
		for (int j = 0; j < size; j++) {
			Random rand = new Random();
			flag.append(sources.charAt(rand.nextInt(9)) + "");
		}
		return flag.toString();
	}

	public static void main(String[] args) {
		System.out.println(randomNumStr(6));
	}
}