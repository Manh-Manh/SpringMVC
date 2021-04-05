package com.manhdn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.entity.orderEntity;
import com.manhdn.service.supplierService;
import com.manhdn.service.orderService;
@Controller
public class orderController extends CommonController<orderEntity> {
	
	@Autowired
	orderService service;
	/**
	 * 
	 * @return
	 */
	@RequestMapping( value = { "/admin/manageOrder"}, method = RequestMethod.GET)
	public ModelAndView manageOrder() {
		service = new orderService();
		mav = new ModelAndView("/admin/order/manageOrder");
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}
}
