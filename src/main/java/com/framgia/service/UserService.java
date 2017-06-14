package com.framgia.service;

import com.framgia.bean.UserInfo;
import com.framgia.security.CustomUserDetail;

public interface UserService extends BaseService {

	CustomUserDetail findByUserName(String username);

	UserInfo findById(Integer id, boolean flagUpdate);

	boolean addUser(UserInfo userInfo);

	boolean isUserExist(UserInfo user);

	boolean updatetUser(UserInfo userInfo);

	boolean removeUser(Integer id, Integer idGroup);

	boolean acceptUserJoinGroup(Integer id, Integer idGroup);
}
