package com.framgia.service.impl;

import org.apache.log4j.Logger;

import com.framgia.bean.GroupInfo;
import com.framgia.model.Group;
import com.framgia.model.Permission;
import com.framgia.model.User;
import com.framgia.security.CustomUserDetail;
import com.framgia.service.GroupService;
import com.framgia.util.Constants;
import com.framgia.util.ConvetBeanAndModel;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

@SuppressWarnings("serial")
public class GroupServiceImpl extends BaseServiceImpl implements GroupService {

	private static final Logger logger = Logger.getLogger(GroupServiceImpl.class);

	@Override
	public GroupInfo findById(Integer id, boolean flgUpdate) {
		try {
			Group group = getGroupDAO().findById(id, flgUpdate);
			return ConvetBeanAndModel.convertGroupModelToBean(group);
		} catch (Exception e) {
			logger.error("Group service _ findById", e);
			return null;
		}
	}

	@Override
	public boolean createGroup(GroupInfo groupInfo) {

		try {
			CustomUserDetail userDetail = Helpers.getUserDetail();

			User user = hasOwnGroup(Integer.parseInt(userDetail.getUserId()));
			if (user != null) {

				groupInfo.setUserCreate(ConvetBeanAndModel.convertUserModelToBean(user));
				groupInfo.setUserUpdate(Helpers.getUsername());
				groupInfo.setDateCreate(DateUtil.getDateNow());
				groupInfo.setDateUpdate(DateUtil.getDateNow());
				groupInfo.setStatus(Constants.GROUP_STATUS_CODE_ACTIVE);
				groupInfo.setDeleteFlag(Constants.DEL_FLG);

				Group group = getGroupDAO().create(ConvetBeanAndModel.convertGroupBeanToModel(groupInfo));

				if (group == null)
					return false;

				// update user
				user.setStatusJoin(Constants.STATUSJOIN_CODE_APPOVE);
				user.setIdGroup(group.getId());
				user.setPermission(new Permission(Constants.PERMISSION_CODE_MANAGER));

				getUserDAO().update(user);
				return true;
			}
		} catch (Exception e) {
			logger.error("add group", e);
		}

		return false;
	}

	protected User hasOwnGroup(int id) {
		try {
			User user = getUserDAO().findById(id, true);
			if (user != null && user.getIdGroup() == null)
				return user;
		} catch (Exception e) {
			logger.error("isUserExist", e);
		}
		return null;
	}

}
