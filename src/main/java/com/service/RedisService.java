package com.service;

import java.util.concurrent.TimeUnit;

import com.dao.vo.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public void set(String key, Object value, long timeSec) {
		this.redisTemplate.opsForValue().set(key, value, timeSec, TimeUnit.SECONDS);
	}

	public String get(String key) {
		return (String) this.redisTemplate.opsForValue().get(key);
	}

	public UserLoginForm getUserLoginForm(String key) {
		Object obj = this.redisTemplate.opsForValue().get(key);
		return (UserLoginForm)obj;
	}

	public void delete(String key){
		this.redisTemplate.delete(key);
	}

}
