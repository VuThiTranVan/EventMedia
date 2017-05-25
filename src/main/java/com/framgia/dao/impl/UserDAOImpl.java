package com.framgia.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.AbstractDAO;
import com.framgia.dao.UserDAO;
import com.framgia.model.User;
import com.framgia.util.Constants;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {

	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public User findByUserName(String username) {
		logger.info("UserDAO _ findByUsername");
		User user = new User();
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));
		crit.add(Restrictions.eq("username", username));
		user = (User) crit.uniqueResult();
		return user;
	}

	@Override
	public void create(User user) {
		logger.info("UserDAO _ createUser");
		saveOrUpdate(user);
		
	}
}
