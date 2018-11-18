package com.service;

import java.util.List;

import com.dao.AlarmDao;
import com.dao.bean.AlarmRecord;

public class AlarmService {

	private AlarmDao alarmDao;

	public List<AlarmRecord> list(String type) {
		// TODO Auto-generated method stub
		List<AlarmRecord> list=this.alarmDao.list(type);
		return list;
	}

}
