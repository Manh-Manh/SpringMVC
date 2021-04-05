package com.manhdn.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.entity.userEntity;
import com.manhdn.service.supplierService;
import com.manhdn.service.userService;

@Controller
public class userController extends CommonController<userEntity> {

	@Autowired
	userService service;

	@RequestMapping(value = { "/app-view/checkOut" }, method = RequestMethod.GET)
	public ModelAndView cart() {
		mav = new ModelAndView("/user/checkOut");
//		service = new productService();
//		dataSelected = service.getProductDetail(id);
//		smav.addObject("dataSelected", dataSelected);
		addData();
		return mav;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageUser" }, method = RequestMethod.GET)
	public ModelAndView manageUser() {
		service = new userService();
		mav = new ModelAndView("/admin/user/manageUser");
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}

	@RequestMapping(value = { "/app-view/login" }, method = RequestMethod.GET)
	public ModelAndView login(HttpSession session) {
		mav = new ModelAndView("/user/login");
		mav.addObject("user", new userEntity());
		return mav;
	}

	@RequestMapping(value = { "/app-view/login" }, method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("userSearch") userEntity dataSearch, HttpSession session) {
		this.dataSearch = dataSearch;
		userEntity user = service.login(this.dataSearch);
		if (user != null) {
			session.setAttribute("user", this.dataSearch);
		}
		mav = new ModelAndView("/user/home");
		addData();
		return mav;
	}
	
	@RequestMapping(value = { "/app-view/logout" }, method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		
		session.removeAttribute("user");
		mav = new ModelAndView("/user/home");
		addData();
		return mav;
	}

	@ModelAttribute("userSearch")
	public userEntity userSearch() {
		return new userEntity();
	}

	@ModelAttribute("userRegister")
	public userEntity userRegister() {
		return new userEntity();
	}
}
