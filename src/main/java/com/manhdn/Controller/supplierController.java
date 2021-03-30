package com.manhdn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.entity.supplierEntity;
import com.manhdn.service.productService;
import com.manhdn.service.supplierService;

@Controller
public class supplierController extends CommonController<supplierEntity>{
	@Autowired
	supplierService service;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping( value = { "/admin/manageSupplier"}, method = RequestMethod.GET)
	public ModelAndView manageSupplier() {
		service = new supplierService();
		mav = new ModelAndView("/admin/manageSupplier");
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}
}
