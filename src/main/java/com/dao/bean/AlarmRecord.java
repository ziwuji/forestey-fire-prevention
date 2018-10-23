package com.dao.bean;

public class AlarmRecord {
	private long aRecId;
	private String alarmTime;
	private long isHangdled;
	private long crewId;
	public long getaRecId() {
		return aRecId;
	}
	public void setaRecId(long aRecId) {
		this.aRecId = aRecId;
	}
	public String getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public long getIsHangdled() {
		return isHangdled;
	}
	public void setIsHangdled(long isHangdled) {
		this.isHangdled = isHangdled;
	}
	public long getCrewId() {
		return crewId;
	}
	public void setCrewId(long crewId) {
		this.crewId = crewId;
	}

}
