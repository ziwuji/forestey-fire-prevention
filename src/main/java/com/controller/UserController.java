package com.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.res.UserRegisterResult;
import com.dao.res.UserRestPwdResult;
import com.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.exceptions.ClientException;
import com.common.CommonResult;
import com.dao.res.UserLoginResult;
import com.dao.vo.UserLoginForm;
import com.service.RedisService;
import com.service.UserService;
import com.util.BaseUtils;
import com.util.CreateYZMCodeUtils;
import com.util.MathUtils;
import com.util.SmsUtils;

/**
 * @author ziwuji
 *@description: �û���¼ע���Լ���֤
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger LOG=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private UserService usersService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * ��ȡ�û���¼��Ϣ(�Ƿ��¼)
	 * 
	 */
	@RequestMapping(value="/info",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult<UserLoginForm> userInfo() {
		String key = this.getUserInfoKey();
		UserLoginForm ulf = this.redisService.getUserLoginForm(key);
		if(ulf == null){
			throw new LoginStatusException();
		}
		return new CommonResult<UserLoginForm>(0, "success", ulf);
	}
	
	/**
	 * ��¼
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public CommonResult<Void> login(@RequestBody UserLoginResult userLoginResult){
		LOG.debug("userLoginResult:{}",userLoginResult);
		if(BaseUtils.isNullOrEmpty(userLoginResult.getPhone())||BaseUtils.isNullOrEmpty(userLoginResult.getPwd())) {
			throw new InvalidRequestException();
		}
		BaseUtils.checkPassword(userLoginResult.getPwd());
		UserLoginForm ulf=this.usersService.login(userLoginResult);
		if(ulf==null) {
			throw new LoginException();
		}
		String key=this.getUserInfoKey();
		this.redisService.set(key, ulf, 24 * 60 * 60);
		return CommonResult.SUCCESS;
	}
	
	/**
	 * ע��
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public CommonResult<Void> register(@RequestBody UserRegisterResult userRegisterResult){
	    LOG.debug("userRegisterResult={}",userRegisterResult);
	    BaseUtils.checkPhone(userRegisterResult.getPhone());
	    checkSmsCode(userRegisterResult.getSmsCode(),userRegisterResult.getPhone());
	    BaseUtils.checkUsername(userRegisterResult.getUsername());
	    BaseUtils.checkEmail(userRegisterResult.getEmail());
	    BaseUtils.checkPassword(userRegisterResult.getPwd());
	    try {
	    	this.usersService.register(userRegisterResult);
	    }catch (DuplicateKeyException e) {
	    	LOG.warn("phone be userd{}",userRegisterResult.getPhone());
	    	throw new PhoneBeUsedException();
	    }
		return CommonResult.SUCCESS;
		
	}
	
	/**
	 * ��������
	 * 
	 */
	@RequestMapping(value="/restpwd",method=RequestMethod.POST)
	@ResponseBody
	public CommonResult<Void> restpwd(@RequestBody UserRestPwdResult userRestPwdResult){
		LOG.debug("userRestPwdResult={}",userRestPwdResult);
		this.checkSmsCode(userRestPwdResult.getSmsCode(), userRestPwdResult.getPhone());
		this.usersService.repwd(userRestPwdResult);
		return CommonResult.SUCCESS;
	}
	
	/**
	 * ��ȡ������֤��
	 * @param smsCode
	 * 
	 */
	@RequestMapping(value="/smscode",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult<Void> smscode(String phone,String imgcode){
		BaseUtils.checkPhone(phone);
		this.checkImgCode(imgcode);
		this.checkFreq(phone);
		String code=MathUtils.randomNumStr(6);
		try {
			SmsUtils.send(phone,code);
			String key=this.getSmscodeKey(phone);
			this.redisService.set(key, code, 60*5);
		}catch (com.aliyuncs.exceptions.ServerException e) {
			throw new ServerException(e);
		}catch(ClientException e) {
			throw new ServerException(e);
		}
		LOG.debug("send msg{} {}",phone,code);
		return CommonResult.SUCCESS;
	}
	
	/**
	 * У�������֤��
	 * 
	 */
	public void checkSmsCode(String smsCode,String phone) {
		String oSmsCode=this.redisService.get(this.getSmscodeKey(phone));
		LOG.debug("smsCode,oSmsCode={}{}",oSmsCode,smsCode);
		if(oSmsCode == null) {
			throw new ExpireSmsCodeException();
		}
		if(!smsCode.equals(oSmsCode)) {
			throw new InvalidSmsCodeException();
		}
	}
	
	/**
	 * �ж��ֻ����Ƿ���һ����֮���ѷ��͹���֤��
	 * @param phone
	 */

	private void checkFreq(String phone) {
		// TODO Auto-generated method stub
		String key= this.getSmscodeKey(phone);
		String code= this.redisService.get(key);
		if(code != null) {
			throw new SendMsgFreException();
		}
	}
	/**
	 * У��ͼƬ��֤��
	 * @param imgCode
	 */

	private void checkImgCode(String imgCode) {
		// TODO Auto-generated method stub
		String oImgCode=this.redisService.get(getImgcodeKey());
		LOG.debug("imgCode,oImgCode={},{}",imgCode,oImgCode);
		if(oImgCode==null) {
			throw new ExpireImgCodeException();
		}
		imgCode  = imgCode.toLowerCase();
		oImgCode=oImgCode.toLowerCase();
		if(!imgCode.equals(oImgCode)) {
			throw new InvalidImgCodeException();
		}
	}
	
	/**
	 * У�������Ƿ�Ψһ
	 * 
	 */
	@RequestMapping(value="/existemail",method=RequestMethod.POST)
	@ResponseBody
    public void checkEmail(String email) {
		boolean b=this.usersService.isUsedEmail(email);
		if(b) {
			throw new EmailBeUsedException();
		}
	}
	
	private String getImgcodeKey() {
		// TODO Auto-generated method stub
		String sessionId=this.request.getSession().getId();
		
		return "imgcode" + sessionId;
	}

	private String getUserInfoKey() {
		// TODO Auto-generated method stub
		return "userinfo:"+this.request.getSession().getId();
	}
	private String getSmscodeKey(String phone) {
		// TODO Auto-generated method stub
		return "smscode:" + phone;
	}
	/**
	 * ��ȡͼƬ��֤��
	 * 
	 */
	@RequestMapping(value="/imgcode",method=RequestMethod.GET)
	@ResponseBody
	public void getImgcode(HttpServletResponse response)throws IOException{
		//������Ӧ����
		response.setContentType("image/jpg");
		//��ȡ������֤�빤������
		CreateYZMCodeUtils yzm =CreateYZMCodeUtils.getInstance();
		//��ȡ���ɵ���֤���ַ���
		String imgCode =yzm.getCreateYZMCode();
		//��imgCode����redis��
		this.redisService.set(this.getImgcodeKey(), imgCode, 60 * 2);
		LOG.debug("{} {}",this.request.getSession().getId(),imgCode);
		//��ȡ��֤��ͼƬ
		BufferedImage img = yzm.getCreateYZMImg(imgCode);
		//ͨ��ImageIOд��ͼƬ
		ImageIO.write(img,"jpg", response.getOutputStream());
	}
	/**
	 * �ǳ�
	 * 
	 */
	@RequestMapping(value = "/logout",method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Void>logout(HttpServletRequest request){
		request.getSession().invalidate();
		this.redisService.delete(this.getUserInfoKey());
		return CommonResult.SUCCESS;
	}
	
	/**
	 * �ֻ�����֤
	 * 
	 */
	@RequestMapping(value = "exist",method = RequestMethod.POST)
	@ResponseBody
	public void exist(String phone) {
		boolean b = this.usersService.isUsedPhone(phone);
		if(b) {
			throw new PhoneBeUsedException();
		}
	}
}
