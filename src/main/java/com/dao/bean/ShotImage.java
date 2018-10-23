package com.dao.bean;

public class ShotImage {
	private long scImgId;
	private String scImgName;
	private long scImgSize;
	private String scImgUrl;
    private String createTime;
    private long scImgType;
    private long vidId;
    private long mptId;
    private long crewId;
	public long getScImgId() {
		return scImgId;
	}
	public void setScImgId(long scImgId) {
		this.scImgId = scImgId;
	}
	public String getScImgName() {
		return scImgName;
	}
	public void setScImgName(String scImgName) {
		this.scImgName = scImgName;
	}
	public long getScImgSize() {
		return scImgSize;
	}
	public void setScImgSize(long scImgSize) {
		this.scImgSize = scImgSize;
	}
	public String getScImgUrl() {
		return scImgUrl;
	}
	public void setScImgUrl(String scImgUrl) {
		this.scImgUrl = scImgUrl;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public long getScImgType() {
		return scImgType;
	}
	public void setScImgType(long scImgType) {
		this.scImgType = scImgType;
	}
	public long getVidId() {
		return vidId;
	}
	public void setVidId(long vidId) {
		this.vidId = vidId;
	}
	public long getMptId() {
		return mptId;
	}
	public void setMptId(long mptId) {
		this.mptId = mptId;
	}
	public long getCrewId() {
		return crewId;
	}
	public void setCrewId(long crewId) {
		this.crewId = crewId;
	}
}
