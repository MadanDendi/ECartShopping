package com.ecart.model.dao;

import com.ecart.model.LoginDetails;

public interface LoginDao {
	public LoginDetails validateLogin(String name,String password);
	public Boolean registerUser(String uid,String pwd,String fname,String phn);

}
