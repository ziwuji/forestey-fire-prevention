package com.dao;

import org.apache.ibatis.annotations.Param;

import com.dao.bean.MonitorVideo;
import com.dao.bean.ShotImage;

public interface ExplorerDao {

	MonitorVideo getMonitorVideo(@Param("aVidId")long aVidId);

	ShotImage getShotImage(@Param("scImgId")long scImgId);
     
}
