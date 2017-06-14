package com.framgia.service;

import com.framgia.bean.DataHighChart;
import com.framgia.bean.GroupInfo;

public interface GroupService extends BaseService {

	GroupInfo findById(Integer id, boolean flgUpdate);

	boolean createGroup(GroupInfo groupInfo);

	boolean updateGroup(GroupInfo groupInfo);

	boolean deleteLogicGroup(Integer id);

	DataHighChart getDataForHighchart(Integer idUser);
}