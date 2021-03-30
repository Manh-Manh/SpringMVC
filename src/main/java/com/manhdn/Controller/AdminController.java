package com.manhdn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.service.productService;

@Controller
public class AdminController{
	
	@Autowired
	productService service;
	
	@RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("/admin/home");
		return mav;
	}
	/**
	 * 
	 * @return
	 */
	
	
}
