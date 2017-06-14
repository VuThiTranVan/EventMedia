package com.framgia.dao;

import java.util.List;

import com.framgia.model.Permission;

/**
 * ManagementUsersController.java
 * 
 * @version 19/04/2017
 * @author vu.thi.tran.van@framgia.com
 */
public interface PermissionDAO extends InterfaceDAO<Integer, Permission> {

	// Search permission with status enable
	List<Permission> findByDelFlg();

}
