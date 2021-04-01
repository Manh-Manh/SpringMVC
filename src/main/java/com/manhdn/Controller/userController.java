package com.manhdn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping( value = { "/admin/manageUser"}, method = RequestMethod.GET)
	public ModelAndView manageUser() {
		service = new userService();
		mav = new ModelAndView("/admin/user/manageUser");
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}
}
