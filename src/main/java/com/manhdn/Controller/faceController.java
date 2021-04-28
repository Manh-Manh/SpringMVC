package com.manhdn.Controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.entity.faceEntity;
import com.manhdn.service.faceService;

@Controller
public class faceController extends CommonController<faceEntity>{
	@Autowired
	faceService service;
	Logger logger = Logger.getLogger(faceController.class);
	/**
	 * 
	 * @return
	 */
	@RequestMapping( value = { "/admin/manageFace"}, method = RequestMethod.GET)
	public ModelAndView manageFace() {
		service = new faceService();
		mav = new ModelAndView("/admin/face/manageFace");
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		logger.info(mav);
		return mav;
	}
}
