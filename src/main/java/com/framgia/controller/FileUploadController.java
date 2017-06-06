package com.framgia.controller;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framgia.bean.FileFormInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.service.FileUploadService;
import com.framgia.util.Helpers;

/**
 * 
 * @author phan.van.hieu@framgia.com
 *
 */
@RestController
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;

	// Log
	private static final Logger logger = Logger.getLogger(FileUploadController.class);

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		logger.info("call page upload image");
		return new ModelAndView("uploadimage");
	}

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public ModelAndView pageImportData(@ModelAttribute("dataImportBean") FileFormInfo dataImportBean,
			RedirectAttributes redirectAttributes, Model m) throws FileNotFoundException {

		Boolean check = true;
		MultipartFile fileUpload = dataImportBean.getFileImport();

		// check empty
		if (Helpers.isEmpty(dataImportBean.getTitle()) || Helpers.isEmpty(dataImportBean.getDescription())
				|| Helpers.isEmpty(dataImportBean.getFileImport().toString())) {
			check = false;
			return new ModelAndView("uploadimage", "err_data", "error");
		}

		// Check maxlength
		if (dataImportBean.getTitle().length() > 50 || dataImportBean.getDescription().length() > 500) {
			check = false;
			return new ModelAndView("uploadimage", "err_data", "error");
		}

		// Check format
		if (!Helpers.checkFormatFile(fileUpload.getOriginalFilename(), "png")
				&& !Helpers.checkFormatFile(fileUpload.getOriginalFilename(), "jpg")
				&& !Helpers.checkFormatFile(fileUpload.getOriginalFilename(), "jpeg")) {
			check = false;
			return new ModelAndView("uploadimage", "err_data", "error");
		}
		if (check) {
			Integer idImage = fileUploadService.uploadImage(dataImportBean, Helpers.getUsername());
			if (idImage == -1) {
				return new ModelAndView("uploadimage", "err_data", "User is exist");
			} else if (idImage != null) {
				ImageInfo imageInfo = fileUploadService.findById(idImage);
				return new ModelAndView("fileUploadSuccess", "imageInfo", imageInfo);
			} else {
				return new ModelAndView("uploadimage", "err_data", "error");
			}
		}
		return new ModelAndView("uploadimage", "err_data", "error");
	}
}