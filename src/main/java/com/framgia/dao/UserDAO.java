package com.framgia.dao;

import java.util.List;

import com.framgia.bean.ConditionUserBean;
import com.framgia.model.User;

public interface UserDAO extends InterfaceDAO<Integer, User> {

	User findByUserName(String username);

	List<User> findByConditon(ConditionUserBean conditionUserBean);

	User findById(Integer id, boolean isLock);

	Long getCountUser(Integer id);
}
