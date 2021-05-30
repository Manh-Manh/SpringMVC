package com.manhdn.Controller;

import java.util.ArrayList;
import java.util.List;

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
import com.manhdn.FunctionCommon;
import com.manhdn.dao.discountDAO;
import com.manhdn.entity.discountEntity;
import com.manhdn.entity.faceEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.faceService;
import com.manhdn.service.productService;

@Controller
public class faceController extends CommonController<faceEntity> {
	@Autowired
	faceService service;
	Logger logger = Logger.getLogger(faceController.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageFace" }, method = RequestMethod.GET)
	public ModelAndView manageFace(HttpSession session) {
		service = new faceService();
		mav = new ModelAndView("/admin/face/manageFace");
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		dataList = service.getAllFace();
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/addFace" }, method = RequestMethod.GET)
	public ModelAndView addFaceView(HttpSession session) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		dataSelected = new faceEntity();
		service = new faceService();
		mav = new ModelAndView("/admin/face/addFace");
		map.addAttribute("isInsert", 1);
//		dataList = service.findDaList(0L, new faceEntity());
//		addAttribute();
		isInsert = true;
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/editFace" }, method = RequestMethod.GET)
	public ModelAndView editFaceView(HttpSession session, @RequestParam("faceId") String faceId) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);

		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if (faceId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new faceService();
		mav = new ModelAndView("/admin/face/addFace");
		dataSelected = service.getDetail(faceId);
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return mav;
	}

	/**
	 * delete
	 * 
	 * @param session
	 * @param faceId
	 * @return
	 */
	@RequestMapping(value = { "/admin/deleteFace" }, method = RequestMethod.GET)
	public ModelAndView deleteFace(HttpSession session, @RequestParam("faceId") String faceId) {

		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if (faceId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new faceService();
		mav = new ModelAndView("/admin/face/manageFace");
		dataSelected = service.getDetail(faceId);
		if (!service.delete(user.getUserId(), faceId)) {
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		session.setAttribute(AppConstants.SESSION_MESSAGE, "Xóa thành công");
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return manageFace(session);
	}

	@RequestMapping(value = { "/admin/addNewFace" }, method = RequestMethod.POST)
	public ModelAndView addNewFace(@ModelAttribute("dataInsert") faceEntity dataInsert, HttpSession session,
			HttpServletRequest request) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new faceService();
		mav = new ModelAndView("/admin/face/addFace");

		if (service.insertOrUpdate(0L, dataInsert)) {
			message = "Cập nhật thành công!";
		} else {
			message = AppConstants.MESSAGE_ERROR;
		}
		mav = new ModelAndView("/admin/product/addProduct");
		session.setAttribute(AppConstants.SESSION_MESSAGE, message);
		dataSelected = service.getDetail(dataInsert.getFaceId());
//		dataList = service.findDaList(0L, new productEntity());
		isInsert = false;
		logger.info(mav);
		return this.editFaceView(session, dataInsert.getFaceId());
//		return mav;
	}

	@ModelAttribute("dataInsert")
	public faceEntity dataInsert() {
		return new faceEntity();
	}
}
