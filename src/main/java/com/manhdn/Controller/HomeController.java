package com.manhdn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	/**
	 * index
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/app-view" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("/user/home");
		return mav;
	}

	/**
	 * @author manhdn view detail
	 * @return
	 */
	@RequestMapping(value = { "/app-view/viewDetail" }, method = RequestMethod.GET)
	public ModelAndView viewDetail() {
		ModelAndView mav = new ModelAndView("/user/viewDetail");
		return mav;
	}

	/**
	 * Login
	 */
	@RequestMapping(value = {"/app-view/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("/user/login");
		return mav;
	}
}
