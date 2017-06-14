package com.framgia.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.framgia.bean.ConditionGroupBean;
import com.framgia.dao.AbstractDAO;
import com.framgia.dao.GroupDAO;
import com.framgia.model.Group;
import com.framgia.util.Constants;
import com.framgia.util.Helpers;

public class GroupDAOImpl extends AbstractDAO<Integer, Group> implements GroupDAO {

	private static final Logger logger = Logger.getLogger(GroupDAOImpl.class);

	public GroupDAOImpl() {
		super(Group.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Group findById(Integer id, boolean isLock) {
		logger.info("Search group to update");
		Criteria crit = getSession().createCriteria(Group.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));

		crit.add(Restrictions.eq("id", id));

		if (isLock) {
			crit.setLockMode(LockMode.UPGRADE);
		}

		return (Group) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> findByGroupType(Integer groupType) {
		Criteria crit = getSession().createCriteria(Group.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));

		crit.add(Restrictions.eq("type", groupType));

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> findByConditon(ConditionGroupBean conditionGroupBean) {
		logger.info("Search list group.");
		Criteria criterion = getSession().createCriteria(Group.class);
		criterion.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));
		if (conditionGroupBean.getNameGroup() != null) {
			if (null != conditionGroupBean.getNameGroup() && !Helpers.isEmpty(conditionGroupBean.getNameGroup())) {
				criterion.add(Restrictions.like("name", "%" + conditionGroupBean.getNameGroup() + "%"));
			}
			if (conditionGroupBean.getTypeGroup() > -1) {
				criterion.add(Restrictions.eq("type", conditionGroupBean.getTypeGroup()));
			}
		}

		return criterion.list();
	}

	@Override
	public Long getCountType(Integer type, Integer status, String deleteFlag) {
		Criteria crit = getSession().createCriteria(Group.class);

		if (type != null) {
			crit.add(Restrictions.eq("type", type));
		}

		if (status != null) {
			crit.add(Restrictions.eq("status", status));
		}

		if (deleteFlag != null) {
			crit.add(Restrictions.eq("deleteFlag", deleteFlag));
		}
		crit.setProjection(Projections.rowCount());

		return (Long) crit.uniqueResult();
	}
}
