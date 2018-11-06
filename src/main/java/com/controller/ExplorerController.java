package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dao.bean.Explorer;
import com.dao.bean.MonitorPoint;
import com.dao.bean.MonitorVideo;
import com.dao.bean.ShotImage;
import com.service.ExplorerService;

/**
 * 
 * @author ziwuji
 *
 */
@RequestMapping("/explorer")
@Controller
public class ExplorerController {
	
	@Autowired
	private ExplorerService explorerService;
	/**
	 * 影像资源管理
	 * 
	 */
	@RequestMapping(value="/video",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject video(long aVidId) {
		
		JSONObject video=new JSONObject();
		MonitorVideo monitorVideo = this.explorerService.getMonitorVideo(aVidId);
		video.put("aVidId", monitorVideo.getmVidId());
		video.put("mVidName", monitorVideo.getmVidName());
		video.put("VidSize", monitorVideo.getVidSize());
		video.put("VidUrl", monitorVideo.getmVidUrl());
		video.put("skchImgUrl", monitorVideo.getSkchImgUrl());
		return video;
	}
	
	@RequestMapping(value="/image",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject image(long scImgId) { 
		JSONObject image=new JSONObject();
		ShotImage shotImage = this.explorerService.getShotImage(scImgId);
		image.put("scImgId", shotImage.getScImgId());
		image.put("scImgId",shotImage.getScImgName());
		image.put("scImgSize", shotImage.getScImgSize());
		image.put("scImgUrl", shotImage.getScImgSize());
		image.put("createTime", shotImage.getCreateTime());
		image.put("scImgType", shotImage.getScImgType());
		image.put("vidId", shotImage.getVidId());
		image.put("mptId", shotImage.getMptId());
		image.put("crewId", shotImage.getCrewId());
		return image;
	}
	
    
}
