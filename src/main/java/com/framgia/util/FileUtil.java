package com.framgia.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.framgia.service.impl.FileUploadServiceImpl;

public interface FileUtil {

	static final Logger logger = Logger.getLogger(FileUploadServiceImpl.class);

	@SuppressWarnings("rawtypes")
	public static Map<String, Object> uploadToCloudinary(Cloudinary cloudinary, MultipartFile sourceFile)
			throws IOException {

		Map<String, Object> cloudinaryUrl = null;
		Map params = ObjectUtils.asMap("public_id",
				Constants.PATH_UPLOAD + sourceFile.getOriginalFilename().split("\\.", 3)[0]);

		// Convert multipart file type image to File type because Cloudinary
		// doesn't accept multipart file type.
		File convFile = multipartToFile(sourceFile);

		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> result = (Map<String, Object>) cloudinary.uploader().upload(convFile, params);
			cloudinaryUrl = result;
		} catch (IOException e) {
			logger.info("Could not upload file to Cloundinary from MultipartFile: ", e);
			throw e;
		}

		return cloudinaryUrl;
	}

	static File multipartToFile(MultipartFile image) throws IllegalStateException, IOException {

		// Convert multipart to file
		File convFile = new File(image.getOriginalFilename());
		image.transferTo(convFile);
		return convFile;
	}

	public static Cloudinary getCloudinaryClient() {
		logger.info("Connecting cloud...");
		return new Cloudinary(ObjectUtils.asMap("cloud_name", Constants.CLOUDINARY_CLOUD_NAME, "api_key",
				Constants.CLOUDINARY_API_KEY, "api_secret", Constants.CLOUDINARY_API_SECRET, "secure", true));
	}

}
