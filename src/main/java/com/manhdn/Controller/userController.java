package com.manhdn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.entity.userEntity;
@Controller
public class userController extends CommonController<userEntity> {
	@RequestMapping(value = { "/app-view/checkOut" }, method = RequestMethod.GET)
	public ModelAndView cart() {
		mav = new ModelAndView("/user/checkOut");
//		service = new productService();
//		dataSelected = service.getProductDetail(id);
//		smav.addObject("dataSelected", dataSelected);
		addData();
		return mav;
	}
}
