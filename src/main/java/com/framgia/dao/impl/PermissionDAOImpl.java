package com.framgia.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.AbstractDAO;
import com.framgia.dao.PermissionDAO;
import com.framgia.model.Permission;
import com.framgia.util.Constants;

/**
 * ManagementUsersController.java
 * 
 * @version 19/04/2017
 * @author vu.thi.tran.van@framgia.com
 */
public class PermissionDAOImpl extends AbstractDAO<Integer, Permission> implements PermissionDAO {

	// log
	private static final Logger logger = Logger.getLogger(PermissionDAOImpl.class);

	public PermissionDAOImpl() {
		super(Permission.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findByDelFlg() {
		logger.info("Search list permission.");

		Criteria criterion = getSession().createCriteria(Permission.class);
		criterion.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));
		return criterion.list();

	}

}
