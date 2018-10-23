package com.dao.bean;

public class MonitorVideo {
	private long mVidId;
	private String mVidName;
	private long VidSize;
	private String mVidUrl;
	private String startTime;
    private String skchImgUrl;
	public long getmVidId() {
		return mVidId;
	}
	public void setmVidId(long mVidId) {
		this.mVidId = mVidId;
	}
	public String getmVidName() {
		return mVidName;
	}
	public void setmVidName(String mVidName) {
		this.mVidName = mVidName;
	}
	public long getVidSize() {
		return VidSize;
	}
	public void setVidSize(long vidSize) {
		VidSize = vidSize;
	}
	public String getmVidUrl() {
		return mVidUrl;
	}
	public void setmVidUrl(String mVidUrl) {
		this.mVidUrl = mVidUrl;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getSkchImgUrl() {
		return skchImgUrl;
	}
	public void setSkchImgUrl(String skchImgUrl) {
		this.skchImgUrl = skchImgUrl;
	}
}
