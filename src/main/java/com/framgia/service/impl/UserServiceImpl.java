package com.framgia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.framgia.bean.UserInfo;
import com.framgia.model.User;
import com.framgia.security.CustomUserDetail;
import com.framgia.service.UserService;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo findById(int id, boolean flagUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editUSer(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteuser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomUserDetail findByUserName(String username) {

		User user = getUserDAO().findByUserName(username);
		if (user != null) {
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
			CustomUserDetail customUser = new CustomUserDetail();
			customUser.setUsername(user.getUsername());
			customUser.setPassword(user.getPassword());
			if (user.getPermission() != null) {
				authList.add(new SimpleGrantedAuthority(user.getPermission().getName()));
				customUser.setAuthorities(authList);
			}

			return customUser;
		}
		return null;
	}

}
