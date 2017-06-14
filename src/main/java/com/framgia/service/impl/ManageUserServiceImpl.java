package com.framgia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.framgia.bean.ConditionUserBean;
import com.framgia.bean.PermissionInfo;
import com.framgia.bean.StatisticalInfo;
import com.framgia.bean.UserInfo;
import com.framgia.model.Group;
import com.framgia.model.Permission;
import com.framgia.model.User;
import com.framgia.service.ManageUserService;
import com.framgia.util.Constants;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

/**
 * ManagementUsersServiceImpl.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@SuppressWarnings("serial")
public class ManageUserServiceImpl extends BaseServiceImpl implements ManageUserService {

	private static final Logger logger = Logger.getLogger(ManageUserServiceImpl.class);

	@Override
	public List<UserInfo> findByUsersWithCondition(ConditionUserBean conditionUserBean) {
		try {
			List<User> userList = userDAO.findByConditon(conditionUserBean);
			if (Helpers.isEmpty(userList)) {
				return null;
			}
			List<UserInfo> userInfo = new ArrayList<UserInfo>();

			for (User item : userList) {

				UserInfo user = new UserInfo();

				user.setId(item.getId());
				user.setUsername(item.getUsername());
				user.setName(item.getName());
				if (Constants.GENDER_CODE_MALE.equals(item.getGender())) {
					user.setGender(Constants.GENDER_VALUE_MALE);
				} else {
					user.setGender(Constants.GENDER_VALUE_FMALE);
				}
				user.setBirthday(item.getBirthday());
				user.setPhone(item.getPhone());
				user.setEmail(item.getEmail());

				PermissionInfo perInfo = new PermissionInfo();

				perInfo.setName(item.getPermission().getName());
				user.setPermission(perInfo);

				userInfo.add(user);

			}
			return userInfo;
		} catch (Exception e) {
			logger.info("Exception at function findByUsersWithCondition in ManageUserServiceImpl: ", e);
		}
		return null;
	}

	@Override
	public List<PermissionInfo> findByDelFlg() {
		try {
			// get value permission role for select box
			List<PermissionInfo> permissionInfo = new ArrayList<PermissionInfo>();

			logger.info("Start find data Permission");
			List<Permission> permissionList = permissionDAO.findByDelFlg();
			if (Helpers.isEmpty(permissionList)) {
				return null;
			}
			for (Permission item : permissionList) {

				PermissionInfo per = new PermissionInfo();
				per.setId(item.getId());
				per.setName(item.getName());
				permissionInfo.add(per);
			}

			return permissionInfo;
		} catch (Exception e) {
			logger.info("Exception at function findByDelFlg in ManageUserServiceImpl: ", e);
		}
		return null;
	}

	@Override
	public UserInfo findById(Integer id) {
		try {
			User user = userDAO.findById(id, false);

			if (user == null) {
				return null;
			}
			UserInfo userInfo = new UserInfo();

			userInfo.setId(user.getId());
			userInfo.setUsername(user.getUsername());
			userInfo.setName(user.getName());
			if (Constants.GENDER_CODE_MALE.equals(user.getGender())) {
				userInfo.setGender(Constants.GENDER_VALUE_MALE);
			} else {
				userInfo.setGender(Constants.GENDER_VALUE_FMALE);
			}
			userInfo.setBirthday(user.getBirthday());
			userInfo.setPhone(user.getPhone());
			userInfo.setEmail(user.getEmail());

			PermissionInfo perInfo = new PermissionInfo();

			perInfo.setName(user.getPermission().getName());
			userInfo.setPermission(perInfo);

			userInfo.setIdGroup(user.getIdGroup());

			if (Constants.STATUSJOIN_CODE_FREE.equals(user.getStatusJoin())) {
				userInfo.setStatusJoin(Constants.STATUSJOIN_VALUE_FREE);
			} else if (Constants.STATUSJOIN_CODE_REQUEST.equals(user.getStatusJoin())) {
				userInfo.setStatusJoin(Constants.STATUSJOIN_VALUE_REQUEST);
			} else {
				userInfo.setStatusJoin(Constants.STATUSJOIN_VALUE_APPOVE);
			}
			userInfo.setDateCreate(user.getDateCreate());
			userInfo.setDateUpdate(user.getDateUpdate());
			userInfo.setUserCreate(user.getUserCreate());
			userInfo.setUserUpdate(user.getUserUpdate());
			return userInfo;
		} catch (Exception e) {
			logger.info("Exception at function findByUsersWithCondition in ManageUserServiceImpl: ", e);
		}
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public Boolean deleteUser(Integer idUser, String userName) {
		try {
			User updUser = userDAO.findById(idUser, true);

			if (updUser.getIdGroup() != 0) {

				Group group = groupDAO.findById(updUser.getIdGroup(), true);

				if (group != null) {
					group.setDeleteFlag(Constants.DEL_FLG_DEL);
					group.setUserUpdate(userName);
					group.setDateUpdate(DateUtil.getDateNow());

					groupDAO.saveOrUpdate(group);
				}
			}

			if (updUser == null) {
				return false;
			}
			updUser.setDeleteFlag(Constants.DEL_FLG_DEL);
			updUser.setUserUpdate(userName);
			updUser.setDateUpdate(DateUtil.getDateNow());

			userDAO.saveOrUpdate(updUser);

			return true;

		} catch (Exception e) {
			logger.error("Error delete logic User: ", e);
			throw e;
		}
	}

	@Override
	public StatisticalInfo getStatisticalInfo(Integer groupType) {
		try {
			List<String> nameGroup = new ArrayList<>();

			List<Long> numberUser = new ArrayList<>();

			List<Group> groupList = groupDAO.findByGroupType(groupType);

			if (groupList == null) {
				return null;
			}
			for (Group group : groupList) {
				nameGroup.add(group.getName());
				numberUser.add(userDAO.getCountUser(group.getId()));
			}
			String nameTypeGroup;
			if (groupType == 0) {
				nameTypeGroup = Constants.GROUP_TYPE_VALUE_PRIVATE;
			} else {
				nameTypeGroup = Constants.GROUP_TYPE_VALUE_PUBLIC;
			}

			return new StatisticalInfo(nameTypeGroup, numberUser, nameGroup);
		} catch (Exception e) {
			logger.error("Exception at function getStatisticalInfo in ManageUserServiceImpl: ", e);
		}
		return null;
	}

}
