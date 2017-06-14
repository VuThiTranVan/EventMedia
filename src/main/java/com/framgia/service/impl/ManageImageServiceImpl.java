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
import com.framgia.model.Image;
import com.framgia.service.ManageImageService;
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
public class ManageImageServiceImpl extends BaseServiceImpl implements ManageImageService {

	private static final Logger logger = Logger.getLogger(ManageImageServiceImpl.class);

	@Autowired
	ImageDAO imageDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	GroupDAO groupDAO;

	@Override
	public ImageInfo findImage(String username) {

		logger.info("get data Image");

		Image image = imageDAO.findImage(username);
		if(image == null){
			return null;
		}
		// Convert data ImageInfo
		ImageInfo imageInfo = ConvetBeanAndModel.convertImageModelToBean(image);
		GroupInfo group = new GroupInfo();
		group.setName(image.getGroup().getName());
		imageInfo.setGroup(group);
		return imageInfo;
	}

	@Override
	public Boolean updateImage(FileFormInfo dataImportBean, String username) {

		// Define Image
		try {
			Image image = imageDAO.findById(dataImportBean.getId(), true);

			if (image == null) {
				return false;
			}
			if (!dataImportBean.getUrl().equals(dataImportBean.getFileName())) {
				logger.info("Upload Image");
				Cloudinary cloudinary = FileUtil.getCloudinaryClient();

				MultipartFile multipartFile = dataImportBean.getFileImport();

				// Check user upload image
				if (multipartFile != null) {
					FileUtil.uploadToCloudinary(cloudinary, multipartFile);
				}

				String url = cloudinary.url().format("jpg")
						.transformation(new Transformation().width(650).height(400).crop("fit"))
						.generate(Constants.PATH_UPLOAD + multipartFile.getOriginalFilename().split("\\.", 3)[0]);

				image.setUrl(url);

			}

			image.setTitle(dataImportBean.getTitle());
			image.setDescription(dataImportBean.getDescription());
			image.setDeleteFlag(Constants.DEL_FLG);
			image.setDateUpdate(DateUtil.getDateNow());
			image.setUserUpdate(username);

			// Insert data into table Image
			imageDAO.saveOrUpdate(image);

			return true;

		} catch (Exception e) {
			logger.info("Exception at function uploadImage in FileUploadServiceImpl: ", e);
		}

		return false;
	}

	@Override
	public Boolean deleteImage(Integer id, String username) {
		try {
			Image image = imageDAO.findById(id, true);

			if (image == null) {
				return false;
			} else {
				image.setDeleteFlag(Constants.DEL_FLG_DEL);
				image.setUserUpdate(username);
				image.setDateUpdate(DateUtil.getDateNow());

				imageDAO.saveOrUpdate(image);
			}

			return true;

		} catch (Exception e) {
			logger.error("Error delete logic Image: ", e);
		}
		return false;
	}

}