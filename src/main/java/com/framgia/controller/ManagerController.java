package com.framgia.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.framgia.bean.GroupInfo;
import com.framgia.bean.UserInfo;
import com.framgia.service.GroupService;
import com.framgia.service.ImageService;
import com.framgia.service.UserService;
import com.framgia.util.Constants;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

/**
 * 
 * @version 30/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@RestController
public class ManagerController {

	private static final Logger logger = Logger.getLogger(ManagerController.class);

	@Autowired
	GroupService groupService;

	@Autowired
	UserService userService;

	@Autowired
	ImageService imageService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = { "/manager" }, method = RequestMethod.GET)
	public ModelAndView initPage() {
		logger.info("Init page");

		GroupInfo groupInfo = new GroupInfo();
		groupInfo.setName("");
		groupInfo.setDescription("");
		groupInfo.setNote("");
		groupInfo.setStatus(Constants.GROUP_STATUS_CODE_ACTIVE);
		groupInfo.setType(Constants.GROUP_TYPE_CODE_PRIVATE);
		groupInfo.setDateStart(null);
		groupInfo.setDateEnd(null);

		return new ModelAndView("initGroup", "group", groupInfo);
	}

	@RequestMapping(value = "/manager/init", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GroupInfo> getGroup() {

		UserInfo user = userService.findById(Helpers.getIdUser(), false);
		if (user == null) {
			return new ResponseEntity<GroupInfo>(HttpStatus.NOT_FOUND);
		}

		GroupInfo group = groupService.findById(user.getIdGroup(), false);

		if (group == null) {
			return new ResponseEntity<GroupInfo>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<GroupInfo>(group, HttpStatus.OK);
	}

	@RequestMapping(value = "/manager/", method = RequestMethod.POST)
	public ResponseEntity<Void> updateGroup(@ModelAttribute("group") GroupInfo group) {

		if (groupService.updateGroup(group)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/manager/{id}", method = RequestMethod.GET)
	public RedirectView deleteLogicGroup(@PathVariable("id") int id) {

		if (groupService.deleteLogicGroup(id)) {
			return new RedirectView("/EventMedia/logout");
		}
		return new RedirectView("error");
	}

	@RequestMapping(value = "/manager/image/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean removeImage(@PathVariable("id") int id) {
		return imageService.removeImageInGroup(id);
	}

	@RequestMapping(value = "/manager/user/remove/{idGroup}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean removeUser(@PathVariable("idGroup") int idGroup, @PathVariable("id") int id) {
		return userService.removeUser(id, idGroup);
	}
	
	@RequestMapping(value = "/manager/user/reject/{idGroup}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean rejectUserJoinGroup(@PathVariable("idGroup") int idGroup, @PathVariable("id") int id) {
		return userService.removeUser(id, idGroup);
	}
	
	@RequestMapping(value = "/manager/user/accept/{idGroup}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean racceptUserJoinGroup(@PathVariable("idGroup") int idGroup, @PathVariable("id") int id) {
		return userService.acceptUserJoinGroup(id, idGroup);
	}
}
