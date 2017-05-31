package com.framgia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.framgia.bean.GroupInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.model.Group;
import com.framgia.model.Image;
import com.framgia.service.ImageService;
import com.framgia.util.Constants;
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

	@Override
	public ImageInfo getImageByUserCreate(String username, Integer idGroup) {
		try {
			return ConvetBeanAndModel.convertImageModelToBean(getImageDAO().getImageByUserCreate(username, idGroup));
		} catch (Exception e) {
			logger.error("get image by userCreate error", e);
		}
		return null;
	}

	@Override
	public List<ImageInfo> getListImage(String condition, int page) {

		try {
			List<Image> listImage = getImageDAO().getListImage(condition, (page - 1) * Constants.NUMBER_PAGE_LIMIT,
			        Constants.NUMBER_PAGE_LIMIT);
			if (listImage == null || listImage.size() == 0)
				return null;

			List<ImageInfo> listImageInfo = new ArrayList<ImageInfo>();

			for (Image item : listImage) {
				ImageInfo image = ConvetBeanAndModel.convertImageModelToBean(item);

				GroupInfo group = new GroupInfo();
				group.setId(item.getGroup().getId());
				group.setName(item.getGroup().getName());
				image.setGroup(group);
				listImageInfo.add(image);

			}

			return listImageInfo;
		} catch (Exception e) {
			logger.error("get list image error", e);
		}
		return null;
	}

	@Override
	public Integer getNoOfRecord(String condition) {
		return getImageDAO().getNoOfRecord(condition);
	}

}