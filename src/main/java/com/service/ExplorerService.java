package com.service;

import java.util.Collections;
import java.util.List;

import com.dao.ExplorerDao;
import com.dao.bean.Explorer;
import com.dao.bean.MonitorVideo;
import com.dao.bean.ShotImage;

public class ExplorerService {

	private ExplorerDao explorerDao;

	public List<MonitorVideo> listVideo(String condition) {
		// TODO Auto-generated method stub
		List<MonitorVideo> listVideo=this.explorerDao.listVideo(condition);
		return listVideo;
	}

	public ShotImage getShotImage(long scImgId) {
		// TODO Auto-generated method stub
		ShotImage shotImage=this.explorerDao.getShotImage(scImgId);
	
		return shotImage;
	}

	public Integer getStatus(long userId) {
		// TODO Auto-generated method stub
		Integer status=0;
		status=this.explorerDao.getStatus(userId);
		return status;
	}

	public List<ShotImage> listImage(String condition) {
		// TODO Auto-generated method stub
		List<ShotImage> listImage=this.explorerDao.listImage(condition);
		return listImage;
	}
}
