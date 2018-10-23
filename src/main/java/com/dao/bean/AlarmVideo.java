package com.dao.bean;

/**
 *@author: ziwuji
 *@description: ±¨¾¯ÊÓÆµ
 */
public class AlarmVideo {
	private long id;
	private String aVidName;
	private String aVidFormat;
	private String aVidSize;
	private String aVidUrl;
	private String skchImgUrl;
	private String startTime;
	private long mptId;
	private long aRecId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getaVidName() {
		return aVidName;
	}
	public void setaVidName(String aVidName) {
		this.aVidName = aVidName;
	}
	public String getaVidFormat() {
		return aVidFormat;
	}
	public void setaVidFormat(String aVidFormat) {
		this.aVidFormat = aVidFormat;
	}
	public String getaVidSize() {
		return aVidSize;
	}
	public void setaVidSize(String aVidSize) {
		this.aVidSize = aVidSize;
	}
	public String getaVidUrl() {
		return aVidUrl;
	}
	public void setaVidUrl(String aVidUrl) {
		this.aVidUrl = aVidUrl;
	}
	public String getSkchImgUrl() {
		return skchImgUrl;
	}
	public void setSkchImgUrl(String skchImgUrl) {
		this.skchImgUrl = skchImgUrl;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public long getMptId() {
		return mptId;
	}
	public void setMptId(long mptId) {
		this.mptId = mptId;
	}
	public long getaRecId() {
		return aRecId;
	}
	public void setaRecId(long aRecId) {
		this.aRecId = aRecId;
	}
}
