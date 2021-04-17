package com.manhdn.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.AppConstants;
import com.manhdn.entity.orderEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.orderService;
import com.manhdn.service.supplierService;
import com.manhdn.service.userService;

@Controller
public class userController extends CommonController<userEntity> {

	@Autowired
	userService service;

		
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
		mav.addObject(AppConstants.SESSION_USER, new userEntity());
		return mav;
	}

	@RequestMapping(value = { "/app-view/login" }, method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("userSearch") userEntity dataSearch, HttpSession session) {
		this.dataSearch = dataSearch;
		userEntity user = service.login(this.dataSearch);
		if (user != null) {
			session.setAttribute(AppConstants.SESSION_USER, user);
			orderService orderS = new orderService();
			orderEntity cart = orderS.findOderByUserId(user.getUserId(), 1);
			if(cart != null) {
				session.setAttribute(AppConstants.SESSION_CART, cart);
			}
		}
		message = "Đăng nhập thành công!";
		mav = new ModelAndView("redirect:/app-view/home-page");
//		return "redirect:/showUser/" ;
		addData();
		return mav;
	}
	
	@RequestMapping(value = { "/app-view/register" }, method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("userRegister") userEntity dataSearch, HttpSession session) {
		this.dataSearch = dataSearch;
		boolean i = service.register(this.dataSearch);
		if(i) {
			userEntity user = service.login(this.dataSearch);
			if (user != null) {
				session.setAttribute(AppConstants.SESSION_USER, user);
				orderService orderS = new orderService();
				orderEntity cart = orderS.findOderByUserId(user.getUserId(), 1);
				if (cart != null) {
					session.setAttribute(AppConstants.SESSION_CART, cart);
				} else {
					session.setAttribute(AppConstants.SESSION_CART, new orderEntity());
				}
			}
			mav = new ModelAndView("redirect:/app-view/home-page");
			message = "Đăng kí thành công!";
			addData();
			return mav;

		} else {
			mav = new ModelAndView("/user/login");
			message = "Đăng kí thất bại!";
			addData();
			return mav;
		}


	}
	
	@RequestMapping(value = { "/app-view/logout" }, method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		
		session.removeAttribute("user");
		mav = new ModelAndView("redirect:/app-view/");
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
	@ModelAttribute("prodSelected")
	public productEntity prodSelected() {
		return new productEntity();
	}
}
