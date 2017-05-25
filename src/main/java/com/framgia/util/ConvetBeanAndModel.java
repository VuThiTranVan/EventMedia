package com.framgia.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.framgia.bean.UserInfo;
import com.framgia.model.Permission;
import com.framgia.model.User;

public class ConvetBeanAndModel {
	public static User converUserBeanToModel(UserInfo userInfo) {
		User user = new User();
		user.setId(userInfo.getId());
		user.setUsername(userInfo.getUsername());
		user.setPassword(passwordEncoderToString(userInfo.getPassword()));
		user.setName(userInfo.getName());
		user.setGender(userInfo.getGender());
		user.setBirthday(userInfo.getBirthday());

		user.setPhone(userInfo.getPhone());
		user.setEmail(userInfo.getEmail());
		user.setStatusJoin(userInfo.getStatusJoin());
		user.setDeleteFlag(Constants.DEL_FLG);
		user.setDateCreate(userInfo.getDateCreate());
		user.setUserCreate(userInfo.getUserUpdate());
		user.setDateUpdate(userInfo.getDateUpdate());
		user.setUserUpdate(userInfo.getUserUpdate());

		if (null != userInfo.getPermission()) {
			Permission permission = new Permission();
			permission.setId(userInfo.getPermission().getId());
		} else {
			Permission permission = new Permission();
			permission.setId(0);
			user.setPermission(permission);
			user.setStatusJoin(Constants.STATUSJOIN_CODE_FREE);
		}
		return user;
	}

	// encode password
	public static String passwordEncoderToString(String token) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(token);
		return hashedPassword;
	}
}
