package com.framgia.dao;

import java.util.List;

import com.framgia.bean.ConditionUserBean;
import com.framgia.model.User;

public interface UserDAO {

	public User findByUserName(String username);

	public void create(User user);

	public List<User> findByConditon(ConditionUserBean conditionUserBean);

	public User findById(Integer id, boolean isLock);

	public void update(User user);
}
