package com.framgia.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.ImageInfo;
import com.framgia.bean.PagingImage;
import com.framgia.service.ImageService;
import com.framgia.util.Constants;
import com.framgia.util.DateUtil;

/**
 * 
 * @version 05/06/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@RestController
public class ImageController {
	private static final Logger logger = Logger.getLogger(ImageController.class);

	@Autowired
	ImageService imageService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = { "/init" }, method = RequestMethod.GET)
	public ModelAndView initPage() {
		logger.info("Init page image");
		List<ImageInfo> listImage = imageService.getListImage(null, Constants.NUMBER_PAGE_DEFAULT);

		ModelAndView mv = new ModelAndView("homeImage", "image", listImage);

		Integer noOfRecord = imageService.getNoOfRecord(null);
		if (noOfRecord == null) {
			mv.addObject("paging", null);
			return mv;
		}

		PagingImage paging = new PagingImage(noOfRecord,
		        (int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), 1, 2, 0);
		mv.addObject("paging", paging);
		mv.addObject("valueSearch", null);

		return mv;
	}

	@RequestMapping(value = "/searchImage", method = RequestMethod.POST)
	public ModelAndView findByCondition(@RequestParam String valueSearch, @RequestParam int noPage) {

		if (noPage == 0) {
			noPage = Constants.NUMBER_PAGE_DEFAULT;
		}

		List<ImageInfo> image = imageService.getListImage(valueSearch, noPage);

		Integer noOfRecord = imageService.getNoOfRecord(valueSearch);

		PagingImage paging = new PagingImage(noOfRecord,
		        (int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), noPage, noPage + 1, noPage - 1);
		Map<String, Object> map = new HashMap<>();
		map.put("image", image);
		map.put("paging", paging);
		ModelAndView model = new ModelAndView("homeImage", "image", image);

		model.addObject("paging", paging);
		model.addObject("valueSearch", valueSearch);
		return model;
	}
}
