package com.framgia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.framgia.bean.ConditionGroupBean;
import com.framgia.bean.GroupInfo;
import com.framgia.bean.StatisticalInfo;
import com.framgia.dao.GroupDAO;
import com.framgia.dao.PermissionDAO;
import com.framgia.dao.UserDAO;
import com.framgia.model.Group;
import com.framgia.service.ManageGroupService;
import com.framgia.util.Constants;
import com.framgia.util.ConvetBeanAndModel;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

/**
 * ManagementUsersServiceImpl.java
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 * 
 */
public class ManageGroupServiceImpl extends BaseServiceImpl implements ManageGroupService {

	// Log
	private static final long serialVersionUID = 1L;

	@Autowired
	PermissionDAO permissionDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	GroupDAO groupDAO;

	private static final Logger logger = Logger.getLogger(ManageGroupServiceImpl.class);

	@Override
	public List<GroupInfo> findByGroupWithCondition(ConditionGroupBean conditionGroupBean) {
		try {
			List<Group> groupList = groupDAO.findByConditon(conditionGroupBean);
			if (Helpers.isEmpty(groupList)) {
				return null;
			}
			List<GroupInfo> groupInfo = new ArrayList<GroupInfo>();

			for (Group item : groupList) {

				GroupInfo group = new GroupInfo();

				group.setId(item.getId());
				group.setName(item.getName());
				group.setDescription(item.getDescription());
				if (Constants.GROUP_TYPE_CODE_PRIVATE.equals(item.getType().toString())) {
					group.setType(Constants.GROUP_TYPE_VALUE_PRIVATE);
				} else {
					group.setType(Constants.GROUP_TYPE_VALUE_PUBLIC);
				}
				if (Constants.GROUP_STATUS_CODE_ACTIVE.equals(item.getStatus().toString())) {
					group.setStatus(Constants.GROUP_STATUS_VALUE_ACTIVE);
				} else {
					group.setStatus(Constants.GROUP_STATUS_VALUE_INACTIVE);
				}
				group.setDateStart(DateUtil.convertDatetoString(item.getDateStart()));
				group.setDateEnd(DateUtil.convertDatetoString(item.getDateEnd()));

				groupInfo.add(group);

			}
			return groupInfo;
		} catch (Exception e) {
			logger.info("Exception at function findByGroupWithCondition in ManageGroupServiceImpl: ", e);
		}
		return null;
	}

	@Override
	public Boolean deleteGroup(Integer id, String userName) {
		try {
			Group group = groupDAO.findById(id, true);

			if (group == null) {
				return false;
			} else {
				group.setDeleteFlag(Constants.DEL_FLG_DEL);
				group.setUserUpdate(userName);
				group.setDateUpdate(DateUtil.getDateNow());

				groupDAO.saveOrUpdate(group);
			}

			return true;

		} catch (Exception e) {
			logger.error("Error delete logic User: ", e);
		}
		return false;
	}

	@Override
	public GroupInfo findById(Integer id) {
		try {
			Group group = groupDAO.findById(id, false);

			if (group == null) {
				return null;
			}
			GroupInfo groupInfo = ConvetBeanAndModel.convertGroupModelToBean(group);

			if (Constants.GROUP_TYPE_CODE_PRIVATE.equals(groupInfo.getType())) {
				groupInfo.setType(Constants.GROUP_TYPE_VALUE_PRIVATE);
			} else {
				groupInfo.setType(Constants.GROUP_TYPE_VALUE_PUBLIC);
			}
			if (Constants.GROUP_STATUS_CODE_ACTIVE.equals(groupInfo.getStatus())) {
				groupInfo.setStatus(Constants.GROUP_STATUS_VALUE_ACTIVE);
			} else {
				groupInfo.setStatus(Constants.GROUP_STATUS_VALUE_INACTIVE);
			}

			return groupInfo;

		} catch (Exception e) {
			logger.info("Exception at function findById in ManageGroupServiceImpl: ", e);
		}
		return null;
	}

	@Override
	public StatisticalInfo getStatisticalInfo(Integer typeStatictical) {
		try {
			List<String> nameGroup = new ArrayList<>();

			List<Long> numberUser = new ArrayList<>();

			if (typeStatictical == 0) {
				nameGroup.add(Constants.GROUP_TYPE_VALUE_PRIVATE);
				numberUser.add(groupDAO.getCountType(Constants.DEFAULT_VALUE_0, null, Constants.DEL_FLG));

				nameGroup.add(Constants.GROUP_TYPE_VALUE_PUBLIC);
				numberUser.add(groupDAO.getCountType(Constants.DEFAULT_VALUE_1, null, Constants.DEL_FLG));
				return new StatisticalInfo(Constants.TYPE, numberUser, nameGroup);
			} else if (typeStatictical == 1) {
				nameGroup.add(Constants.GROUP_STATUS_VALUE_ACTIVE);
				numberUser.add(groupDAO.getCountType(null, Constants.DEFAULT_VALUE_0, Constants.DEL_FLG));

				nameGroup.add(Constants.GROUP_STATUS_VALUE_INACTIVE);
				numberUser.add(groupDAO.getCountType(null, Constants.DEFAULT_VALUE_1, Constants.DEL_FLG));
				return new StatisticalInfo(Constants.STATUS, numberUser, nameGroup);
			} else {
				nameGroup.add(Constants.NOT_DELETE);
				numberUser.add(groupDAO.getCountType(null, null, Constants.DEL_FLG));

				nameGroup.add(Constants.DELETED);
				numberUser.add(groupDAO.getCountType(null, null, Constants.DEL_FLG_DEL));
				return new StatisticalInfo(Constants.DELETE_FLAG, numberUser, nameGroup);
			}
		} catch (Exception e) {
			logger.error("Exception at function getStatisticalInfo in ManageGroupServiceImpl: ", e);
		}
		return null;
	}

}
