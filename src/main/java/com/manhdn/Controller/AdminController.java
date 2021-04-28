package com.manhdn.Controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.service.productService;
import com.mysql.cj.log.Log;

@Controller
public class AdminController{
	
	@Autowired
	productService service;
	Logger logger = Logger.getLogger(AdminController.class);
	@RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("/admin/home");
		logger.info(mav);
		return mav;
	}
	/**
	 * 
	 * @return
	 */
	
	
}
