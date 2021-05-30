package com.manhdn.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.discountEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.discountService;
import com.manhdn.service.productService;

@Controller
public class discountController extends CommonController<discountEntity> {
	@Autowired
	discountService service;
	private Logger logger = Logger.getLogger(discountController.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageDiscount" }, method = RequestMethod.GET)
	public ModelAndView manageDiscount(HttpSession session) {
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new discountService();
		mav = new ModelAndView("/admin/discount/manageDiscount");
		dataList = service.getAllDiscount();
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/addDiscount" }, method = RequestMethod.GET)
	public ModelAndView addDiscountView(HttpSession session) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}

		dataSelected = new discountEntity();

		service = new discountService();
		mav = new ModelAndView("/admin/discount/addDiscount");
		map.addAttribute("isInsert", 1);
//		dataList = service.findDaList(0L, new discountEntity());
//		addAttribute();
		isInsert = true;
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/editDiscount" }, method = RequestMethod.GET)
	public ModelAndView editDiscountView(HttpSession session, @RequestParam("discountId") String discountId) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);

		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if (discountId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new discountService();
		mav = new ModelAndView("/admin/discount/addDiscount");
		dataSelected = service.getDetail(discountId);

		isInsert = false;
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return mav;
	}

	/**
	 * delete
	 * 
	 * @param session
	 * @param discountId
	 * @return
	 */
	@RequestMapping(value = { "/admin/deleteDiscount" }, method = RequestMethod.GET)
	public ModelAndView deleteDiscount(HttpSession session, @RequestParam("discountId") String discountId) {

		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if (discountId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new discountService();
		mav = new ModelAndView("/admin/discount/manageDiscount");
		dataSelected = service.getDetail(discountId);
		if (!service.delete(user.getUserId(), discountId)) {
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		session.setAttribute(AppConstants.SESSION_MESSAGE, "Xóa thành công");
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return manageDiscount(session);
	}

	@RequestMapping(value = { "/admin/addNewDiscount" }, method = RequestMethod.POST)
	public ModelAndView addNewDiscount(@ModelAttribute("dataInsert") discountEntity dataInsert, HttpSession session,
			HttpServletRequest request) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new discountService();
		mav = new ModelAndView("/admin/discount/addDiscount");

		if (service.insertOrUpdate(user.getUserId(), dataInsert)) {
			message = "Cập nhật thành công!";
		} else {
			message = AppConstants.MESSAGE_ERROR;
		}
		mav = new ModelAndView("/admin/product/addProduct");
		session.setAttribute(AppConstants.SESSION_MESSAGE, message);
		if (!FunctionCommon.isEmpty(dataInsert.getDiscountId())) {
			dataSelected = service.getDetail(dataInsert.getDiscountId());
		} else {
			dataSelected = dataInsert;
		}

//		dataList = service.findDaList(0L, new productEntity());
		isInsert = false;
		logger.info(mav);
		return this.editDiscountView(session, dataInsert.getDiscountId());
//		return mav;
	}

	@ModelAttribute("dataInsert")
	public discountEntity dataInsert() {
		return new discountEntity();
	}
}
