package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.dao.bean.User;
public interface UserDao {
	User selectByPhone(@Param("phone") String phone);
	User selectByEmail(@Param("email") String email);
	User selectByUserId(@Param("userId") long userId);
	
    //public List <User> list(@Param("userId")long userId);
    public Integer isAdministrator(@Param("userId")long userId);
    public void setAdministrator(@Param("userId") long userId,@Param("status")int status);
    long  insertUser(User u);
	void updatePwdByPhone(String phone, String pwd);
}
