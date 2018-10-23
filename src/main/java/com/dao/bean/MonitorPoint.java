package com.dao.bean;

public class MonitorPoint {
	private long id;
	private String mptIP;
	private String mptName;
	private double lattitude;
	private double longtitude;
	private double height;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMptIP() {
		return mptIP;
	}
	public void setMptIP(String mptIP) {
		this.mptIP = mptIP;
	}
	public String getMptName() {
		return mptName;
	}
	public void setMptName(String mptName) {
		this.mptName = mptName;
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
}
