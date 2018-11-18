package com.service;


import java.util.List;

import javax.jms.ResourceAllocationRuntimeException;

import java.util.Date;

import org.omg.PortableInterceptor.IORInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;

import com.dao.UserDao;
import com.dao.bean.User;
import com.dao.res.UserLoginResult;
import com.dao.res.UserRegisterResult;
import com.dao.res.UserRestPwdResult;
import com.dao.vo.UserLoginForm;
import com.exception.ProhibitUserException;
import com.exception.PwdCannotSameException;
import com.util.BaseUtils;

public class UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private RedisService redisService;
	@Autowired
	private UserDao userDao;
	
	public UserLoginForm login(UserLoginResult userLoginResult) {
		// TODO Auto-generated method stub
		User user = this.userDao.selectByPhone(userLoginResult.getPhone());
		if(user == null) {
			LOG.warn("not user {}",userLoginResult.getPhone());
			return null;
		}
		if(!user.getPwd().equals(BaseUtils.md5(userLoginResult.getPwd()))) {
		    LOG.warn("error pwd!{} {} {}",userLoginResult.getPhone(),userLoginResult.getPwd(),BaseUtils.md5(userLoginResult.getPwd()));
		    return null;
		}
	if(user.getStatus()!=0) {
		throw new ProhibitUserException();
	}
	UserLoginForm ulf = new UserLoginForm();
	ulf.setId(user.getUserId());
	ulf.setUsername(user.getUsername());
	ulf.setHeadImg(user.getHeadImg());
	return ulf;
	/*public List<User> list(long userId,String username,String pwd){
		List<User>user= this.userDao.list(userId,username,pwd);
		if(user==null) {
			LOG.debug("not user {}",list(userId, username, pwd));
			return null;
		}
		return this.userDao.list(userId, username, pwd);*/
		
	}

	public void register(UserRegisterResult userRegisterResult) {
		// TODO Auto-generated method stub
		User u = new User();
		u.setDepartment(userRegisterResult.getDepartment());
		u.setPhone(userRegisterResult.getPhone());
		u.setUsername(userRegisterResult.getUsername());
		u.setPwd(userRegisterResult.getPwd());
		u.setEmail(userRegisterResult.getEmail());
		u.setStatus(0);
		this.userDao.insertUser(u);
	}

	public void repwd(UserRestPwdResult userRestPwdResult) {
		// TODO Auto-generated method stub
		String pwd=this.userDao.selectByPhone(userRestPwdResult.getPhone()).getPwd();
		LOG.warn("pwd is {} {}",BaseUtils.md5(userRestPwdResult.getPwd()),pwd);
		if(BaseUtils.md5(userRestPwdResult.getPwd()).equals(pwd)){
			throw new PwdCannotSameException();
		}
		this.updatePwdByPhone(userRestPwdResult.getPhone(),BaseUtils.md5(userRestPwdResult.getPwd()));
	}

	private void updatePwdByPhone(String phone, String pwd) {
		// TODO Auto-generated method stub
		this.userDao.updatePwdByPhone(phone,pwd);
	}

	public boolean isUsedEmail(String email) {
		// TODO Auto-generated method stub
		return this.userDao.selectByEmail(email) != null;
	}

	public boolean isUsedPhone(String phone) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPhone(phone) != null;
	}
	public UserLoginForm getUserLoginForm(String sessionid) {
		String key = this.getUserInfoKey(sessionid);
		return  this.redisService.getUserLoginForm(key);
	}

	private String getUserInfoKey(String sessionid) {
		// TODO Auto-generated method stub
		return "userinfo:"+sessionid;
	}

	public boolean isAdministrator(long userId) {
		// TODO Auto-generated method stub
		Integer status = this.userDao.isAdministrator(userId);
		return status !=null && status.intValue()==1;
	}

	public void setAdministrator(long userId, boolean administrator) {
		// TODO Auto-generated method stub
		int status;
		if(administrator==false) {
			status=0;
		}else {
			status=1;
		}
		this.userDao.setAdministrator(userId, status);
	}
	
}
