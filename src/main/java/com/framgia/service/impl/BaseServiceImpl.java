package com.framgia.service.impl;

import com.framgia.dao.GroupDAO;
import com.framgia.dao.ImageDAO;
import com.framgia.dao.PermissionDAO;
import com.framgia.dao.UserDAO;
import com.framgia.service.BaseService;;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@SuppressWarnings("serial")
public class BaseServiceImpl implements BaseService {
	protected UserDAO userDAO;
	protected PermissionDAO permissionDAO;
	protected GroupDAO groupDAO;
	protected ImageDAO imageDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public PermissionDAO getPermissionDAO() {
		return permissionDAO;
	}

	public void setPermissionDAO(PermissionDAO permissionDAO) {
		this.permissionDAO = permissionDAO;
	}

	public GroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}

}