package com.framgia.dao;

import com.framgia.model.Group;

public interface GroupDAO {

	public Group findById(Integer id, boolean isLock);

	public void updateGroup(Group group);

	public Group create(Group group);
}
