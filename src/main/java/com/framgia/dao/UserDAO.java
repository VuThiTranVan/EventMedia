package com.framgia.dao;

import com.framgia.model.User;

public interface UserDAO {

	public User findByUserName(String username);

	void create(User user);
}
