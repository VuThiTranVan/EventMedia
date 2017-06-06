package com.framgia.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.dao.impl.UserDAOImpl;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/loginfail", method = RequestMethod.GET)
	public ModelAndView loginFail() {
		try {
			ModelAndView model = new ModelAndView();
			model.addObject("error", messageSource.getMessage("login_fail", null, Locale.getDefault()));
			model.setViewName("login");

			return model;
		} catch (Exception e) {
			logger.error("Fail call controller:", e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		logger.info("Call page home Admin");

		return new ModelAndView("homepageadmin");
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView userPage() {
		logger.info("Call page home User");

		return new ModelAndView("homepageuser");
	}
}
