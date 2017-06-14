package com.framgia.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.framgia.bean.FileFormInfo;
import com.framgia.bean.GroupInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.dao.GroupDAO;
import com.framgia.dao.ImageDAO;
import com.framgia.dao.UserDAO;
import com.framgia.model.Group;
import com.framgia.model.Image;
import com.framgia.model.User;
import com.framgia.service.FileUploadService;
import com.framgia.util.Constants;
import com.framgia.util.ConvetBeanAndModel;
import com.framgia.util.DateUtil;
import com.framgia.util.FileUtil;

/**
 * 
 * @author phan.van.hieu@framgia.com
 *
 */
@SuppressWarnings("serial")
public class FileUploadServiceImpl extends BaseServiceImpl implements FileUploadService {

	private static final Logger logger = Logger.getLogger(FileUploadServiceImpl.class);

	@Autowired
	ImageDAO imageDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	GroupDAO groupDAO;

	@Override
	public Integer uploadImage(FileFormInfo dataImportBean, String username) {

		logger.info("Upload Image");
		Cloudinary cloudinary = FileUtil.getCloudinaryClient();

		MultipartFile multipartFile = dataImportBean.getFileImport();

		try {
			// Check user upload image
			if (imageDAO.findByUserCreate(username) > 0) {
				return -1;
			}
			if (multipartFile != null) {
				FileUtil.uploadToCloudinary(cloudinary, multipartFile);
			}

			String url = cloudinary.url().format("jpg")
					.transformation(new Transformation().width(650).height(400).crop("fit"))
					.generate(Constants.PATH_UPLOAD + multipartFile.getOriginalFilename().split("\\.", 3)[0]);

			User user = userDAO.findByUserName(username);

			// Get Group
			Group dataGroup = groupDAO.findById(user.getIdGroup(), false);
			if (dataGroup == null) {
				return null;
			}
			// Define Image
			Image image = new Image();
			image.setGroup(dataGroup);
			image.setUrl(url);
			image.setTitle(dataImportBean.getTitle());
			image.setDescription(dataImportBean.getDescription());
			image.setDeleteFlag(Constants.DEL_FLG);
			image.setDateCreate(DateUtil.getDateNow());
			image.setDateUpdate(DateUtil.getDateNow());
			image.setUserCreate(username);
			image.setUserUpdate(username);

			// Insert data into table Image
			imageDAO.saveOrUpdate(image);

			return image.getId();
		} catch (Exception e) {
			logger.info("Exception at function uploadImage in FileUploadServiceImpl: ", e);
		}

		return null;
	}

	@Override
	public ImageInfo findById(Integer idImage) {

		logger.info("Get detail Image");
		Image image = imageDAO.findById(idImage, false);

		if (image == null) {
			return null;
		}
		// Convert data ImageInfo
		ImageInfo imageInfo = ConvetBeanAndModel.convertImageModelToBean(image);
		GroupInfo group = new GroupInfo();
		group.setName(image.getGroup().getName());
		imageInfo.setGroup(group);
		
		// Return data
		return imageInfo;

	}
}