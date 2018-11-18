package com.controller;

import java.util.List;

import javax.ws.rs.DELETE;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.model.AddBucketCnameRequest;
import com.dao.bean.Explorer;
import com.dao.bean.MonitorPoint;
import com.dao.bean.MonitorVideo;
import com.dao.bean.ShotImage;
import com.service.ExplorerService;
import com.service.UserService;

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
	private UserService userService;
	/**
	 * 影像资源管理
	 * 
	 */
	@RequestMapping(value="/video",method = RequestMethod.GET)
	@ResponseBody
	public JSONArray listVideo(String condition) {
		
		JSONArray ja = new JSONArray();
		JSONObject jo=new JSONObject();
		List<MonitorVideo> listVideo = this.explorerService.listVideo(condition);
		for (MonitorVideo video:listVideo) {
			jo.put("aVidId", video.getmVidId());
			jo.put("mVidName", video.getmVidName());
			jo.put("VidSize", video.getVidSize());
			jo.put("VidUrl", video.getmVidUrl());
			jo.put("skchImgUrl", video.getSkchImgUrl());
			ja.add(jo);
		}
		return ja;
	}
	
	@RequestMapping(value="/image",method = RequestMethod.GET)
	@ResponseBody
	public JSONArray listImage(String condition) { 
		JSONArray ja=new JSONArray();
		JSONObject jo=new JSONObject();
		//ShotImage shotImage = this.explorerService.getShotImage(scImgId);
		List<ShotImage> listImage=this.explorerService.listImage(condition);
		for(ShotImage Image:listImage) {
			jo.put("scImgId", Image.getScImgId());
			jo.put("scImgId",Image.getScImgName());
			jo.put("scImgSiz"
					+ "e", Image.getScImgSize());
			jo.put("scImgUrl", Image.getScImgSize());
			jo.put("createTime", Image.getCreateTime());
			jo.put("scImgType", Image.getScImgType());
			jo.put("vidId", Image.getVidId());
			jo.put("mptId", Image.getMptId());
			jo.put("crewId", Image.getCrewId());
		    ja.add(jo);
		}
		return ja;
	}
	@RequestMapping(value="/update",method = RequestMethod.POST)
	@ResponseBody
	public void delete(long userId) {
		Integer status=this.explorerService.getStatus(userId);
		if(status==0) {
			return ;
		}
		else {
			
		}
		
	}
   
}
