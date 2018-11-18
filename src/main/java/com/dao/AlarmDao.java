package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dao.bean.AlarmRecord;

public interface AlarmDao {

	List<AlarmRecord> list(@Param("type")String type);

}
