package com.manhdn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.entity.machineEntity;
import com.manhdn.service.productService;
import com.manhdn.service.machineService;

@Controller
public class machineController extends CommonController<machineEntity>{
	@Autowired
	machineService service;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping( value = { "/admin/manageMachine"}, method = RequestMethod.GET)
	public ModelAndView manageMachine() {
		service = new machineService();
		mav = new ModelAndView("/admin/machine/manageMachine");
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}
}
