package com.framgia.dao;

import java.util.List;

import com.framgia.bean.ConditionGroupBean;
import com.framgia.model.Group;

public interface GroupDAO extends InterfaceDAO<Integer, Group> {

	Group findById(Integer id, boolean isLock);

	List<Group> findByGroupType(Integer groupType);

	List<Group> findByConditon(ConditionGroupBean conditionGroupBean);

	Long getCountType(Integer type, Integer status, String deleteFlag);
}
