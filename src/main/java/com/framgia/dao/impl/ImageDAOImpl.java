package com.framgia.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.framgia.dao.AbstractDAO;
import com.framgia.dao.ImageDAO;
import com.framgia.model.Image;
import com.framgia.util.Constants;

public class ImageDAOImpl extends AbstractDAO<Integer, Image> implements ImageDAO {

	private static final Logger logger = Logger.getLogger(ImageDAOImpl.class);

	@SuppressWarnings("deprecation")
	@Override
	public Image findById(Integer id, boolean isLock) {
		logger.info("Search group to update");
		Criteria crit = getSession().createCriteria(Image.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));

		crit.add(Restrictions.eq("id", id));

		if (isLock) {
			crit.setLockMode(LockMode.UPGRADE);
		}

		return (Image) crit.uniqueResult();
	}

	@Override
	public void update(Image image) {
		logger.info("Update iamge");
		saveOrUpdate(image);
	}

	@Override
	public Image getImageByUserCreate(String userCreate, Integer idGroup) {
		logger.info("Search group to update");
		Criteria crit = getSession().createCriteria(Image.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));
		crit.add(Restrictions.eq("userCreate", userCreate));
		crit.createCriteria("group", "group");
		crit.add(Restrictions.eq("group.id", idGroup));

		return (Image) crit.uniqueResult();
}

	public Long findByUserCreate(String username) {
		logger.info("Search group to update");
		Criteria crit = getSession().createCriteria(Image.class);
		crit.add(Restrictions.eq("deleteFlag", Constants.DEL_FLG));

		crit.add(Restrictions.eq("userCreate", username));

		crit.setProjection(Projections.rowCount());
		return (Long) crit.uniqueResult();
	}

}
