package com.framgia.service.impl;

import org.apache.log4j.Logger;

import com.framgia.bean.ImageInfo;
import com.framgia.model.Group;
import com.framgia.model.Image;
import com.framgia.service.ImageService;
import com.framgia.util.ConvetBeanAndModel;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

@SuppressWarnings("serial")
public class ImageServiceImpl extends BaseServiceImpl implements ImageService {

	private static final Logger logger = Logger.getLogger(GroupServiceImpl.class);

	Integer idFreeGroup = 0;

	@Override
	public ImageInfo findById(Integer id, boolean flgUpdate) {
		try {
			Image image = getImageDAO().findById(id, flgUpdate);
			return ConvetBeanAndModel.convertImageModelToBean(image);
		} catch (Exception e) {
			logger.error("Group service _ findById", e);
			return null;
		}
	}

	@Override
	public boolean removeImageInGroup(Integer id) {
		try {
			Image image = getImageDAO().findById(id, true);
			image.setDateUpdate(DateUtil.getDateNow());
			image.setUserUpdate(Helpers.getUsername());
			Group group = new Group();
			group.setId(idFreeGroup);
			image.setGroup(group);

			getImageDAO().update(image);
			return true;
		} catch (Exception e) {
			logger.error("remove image throw out group error", e);
		}
		return false;
	}

}
