package com.dao.vo;

import java.io.Serializable;

/**
 * @Auther: ziwuji
 * @Description:
 */
public class UserLoginForm implements Serializable {
	private Long id;
	private String username;
	private String headImg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
