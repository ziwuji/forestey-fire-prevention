package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.service.ExplorerService;

/**
 * 
 * @author ziwuji
 *
 */
@RequestMapping("/explorer")
@Controller
public class ExplorerController {
	
	private ExplorerService explorerService;
	/**
	 * 影像资源管理
	 * 
	 */
	@RequestMapping(value="/video",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject list (long aVidId,String aVidname) {}
	
	
	
    
}
