package com.framgia.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.framgia.bean.UserInfo;
import com.framgia.model.User;
import com.framgia.security.CustomUserDetail;
import com.framgia.service.UserService;
import com.framgia.util.Constants;
import com.framgia.util.ConvetBeanAndModel;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfo findById(Integer id, boolean flagUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editUSer(UserInfo userInfo) {
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
		try {
			User user = getUserDAO().findByUserName(username);
			if (user != null) {
				List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
				CustomUserDetail customUser = new CustomUserDetail();
				customUser.setUserId(user.getId().toString());
				customUser.setUsername(user.getUsername());
				customUser.setPassword(user.getPassword());
				if (user.getPermission() != null) {
					authList.add(new SimpleGrantedAuthority(user.getPermission().getName()));
					customUser.setAuthorities(authList);
				}

				return customUser;
			}
		} catch (Exception e) {
			logger.error("findByUserName", e);
		}
		return null;
	}

	@Override
	public boolean addUser(UserInfo userInfo) throws ParseException {
		try {
			if (StringUtils.isBlank(Helpers.getUsername())) {

				// Guest register
				userInfo.setUserCreate(userInfo.getUsername());
				userInfo.setUserUpdate(userInfo.getUsername());
			} else {

				// Manger of register
				userInfo.setUserCreate(Helpers.getUsername());
				userInfo.setUserUpdate(Helpers.getUsername());
			}
			userInfo.setDateCreate(DateUtil.getDateNow());
			userInfo.setDateUpdate(DateUtil.getDateNow());
			userInfo.setDeleteFlag(Constants.DEL_FLG);
			User user = ConvetBeanAndModel.convertUserBeanToModel(userInfo);
			getUserDAO().create(user);
			return true;
		} catch (Exception e) {
			logger.error("addUser", e);
		}
		return false;
	}

	@Override
	public boolean isUserExist(UserInfo userInfo) {
		try {
			User user = getUserDAO().findByUserName(userInfo.getUsername());
			if (user != null)
				return true;
		} catch (Exception e) {
			logger.error("isUserExist", e);
		}
		return false;
	}

	@Override
	public boolean updatetUser(UserInfo userInfo) throws ParseException {
		try {
			User user = ConvetBeanAndModel.convertUserBeanToModel(userInfo);
			getUserDAO().update(user);
			return true;
		} catch (Exception e) {
			logger.error("update user", e);
		}
		return false;
	}
}
