package com.framgia.service;

import java.text.ParseException;
import java.util.List;

import com.framgia.bean.UserInfo;
import com.framgia.security.CustomUserDetail;

public interface UserService extends BaseService {
	public List<UserInfo> findAll();

	public CustomUserDetail findByUserName(String username);

	public UserInfo findById(Integer id, boolean flagUpdate);

	public boolean editUSer(UserInfo userInfo);

	public boolean addUser(UserInfo userInfo) throws ParseException;

	public boolean deleteuser(UserInfo userInfo);

	public boolean isUserExist(UserInfo user);

	boolean updatetUser(UserInfo userInfo) throws ParseException;
}
