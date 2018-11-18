package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.bean.Alarm;
import com.dao.bean.AlarmRecord;
import com.service.AlarmService;

public class AlarmController {
	private AlarmService alarService;
	/**
	 * 报警记录查询
	 * 
	 */

	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray list(String type) {
		JSONArray ja=new JSONArray();
		JSONObject jo=new JSONObject();
		List<AlarmRecord> list=this.alarService.list(type);
		for(AlarmRecord record:list) {
			jo.put("aRecId", record.getaRecId());
			jo.put("alarmTime", record.getAlarmTime());
			jo.put("isHangdled", record.getIsHangdled());
			jo.put("crewId", record.getCrewId());
			ja.add(jo);
		}
		return ja;
	}
	@RequestMapping(value="/statistics",method=RequestMethod.GET)
	@ResponseBody
	public void statistics () {
		
	}
 
}
