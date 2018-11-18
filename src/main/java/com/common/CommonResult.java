																															package com.common;

/**
 * @Auther: ziwuji
 * @Description: HTTP请求返回JSON格式公共类
 */

public class CommonResult<T> {

	public final static CommonResult<Void> SUCCESS = new CommonResult<Void>(0, "success");
	
	private int code;
	private String msg;
	private T data;
	private long time;
																					
	public CommonResult(int code, String msg) {
		this(code, msg, null);
	}

	public CommonResult(int code, String msg,T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.time = System.currentTimeMillis();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
