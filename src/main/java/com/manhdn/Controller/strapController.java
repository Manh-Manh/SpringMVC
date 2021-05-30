package com.manhdn.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.AppConstants;
import com.manhdn.entity.strapEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.faceService;
import com.manhdn.service.strapService;

@Controller
public class strapController extends CommonController<strapEntity> {
	@Autowired
	strapService service;
	Logger logger = Logger.getLogger(strapController.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageStrap" }, method = RequestMethod.GET)
	public ModelAndView manageStrap(HttpSession session) {
		service = new strapService();
		mav = new ModelAndView("/admin/strap/manageStrap");
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, "Liên hệ quản trị hệ thống để được hỗ trợ");
			return mav;
		}
		dataList = service.getAllStrap();
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/addStrap" }, method = RequestMethod.GET)
	public ModelAndView addStrapView(HttpSession session) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		dataSelected = new strapEntity();
		service = new strapService();
		mav = new ModelAndView("/admin/strap/addStrap");
		map.addAttribute("isInsert", 1);
//		dataList = service.findDaList(0L, new strapEntity());
//		addAttribute();
		isInsert = true;
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/editStrap" }, method = RequestMethod.GET)
	public ModelAndView editStrapView(HttpSession session, @RequestParam("strapId") String strapId) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);

		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if (strapId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new strapService();
		mav = new ModelAndView("/admin/strap/addStrap");
		dataSelected = service.getDetail(strapId);
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/addNewStrap" }, method = RequestMethod.POST)
	public ModelAndView addNewStrap(@ModelAttribute("dataInsert") strapEntity dataInsert, HttpSession session,
			HttpServletRequest request) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new strapService();
		mav = new ModelAndView("/admin/strap/addStrap");

		if (service.insertOrUpdate(user.getUserId(), dataInsert)) {
			message = "Cập nhật thành công!";
		} else {
			message = AppConstants.MESSAGE_ERROR;
		}
		mav = new ModelAndView("/admin/product/addProduct");
		session.setAttribute(AppConstants.SESSION_MESSAGE, message);
		dataSelected = service.getDetail(dataInsert.getStrapId());
//		dataList = service.findDaList(0L, new productEntity());
		isInsert = false;
		logger.info(mav);
		return this.editStrapView(session, dataInsert.getStrapId());
//		return mav;
	}

	@RequestMapping(value = { "/admin/deleteStrap" }, method = RequestMethod.GET)
	public ModelAndView deleteStrap(HttpSession session, @RequestParam("strapId") String strapId) {

		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if (strapId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new strapService();
		mav = new ModelAndView("/admin/strap/manageStrap");
		dataSelected = service.getDetail(strapId);
		if (!service.delete(user.getUserId(), strapId)) {
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		session.setAttribute(AppConstants.SESSION_MESSAGE, "Xóa thành công");
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return manageStrap(session);
	}

	@ModelAttribute("dataInsert")
	public strapEntity dataInsert() {
		return new strapEntity();
	}
}
