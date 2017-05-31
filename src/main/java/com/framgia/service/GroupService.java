package com.framgia.service;

import com.framgia.bean.GroupInfo;

public interface GroupService extends BaseService {
	public GroupInfo findById(Integer id, boolean flgUpdate);

	public boolean createGroup(GroupInfo groupInfo);

	public boolean updateGroup(GroupInfo groupInfo);

	public boolean deleteLogicGroup(Integer id);
}