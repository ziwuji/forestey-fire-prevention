package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dao.bean.MonitorVideo;
import com.dao.bean.ShotImage;

public interface ExplorerDao {

	
	ShotImage getShotImage(@Param("scImgId")long scImgId);

	List<MonitorVideo> listVideo(@Param("condition")String condition);

	Integer getStatus(@Param("userId")long userId);

	List<ShotImage> listImage(String condition);
     
}
