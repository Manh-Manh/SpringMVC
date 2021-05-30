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
import com.manhdn.entity.machineEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.faceService;
import com.manhdn.service.machineService;

@Controller
public class machineController extends CommonController<machineEntity> {
	@Autowired
	machineService service;
	Logger logger = Logger.getLogger(machineController.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageMachine" }, method = RequestMethod.GET)
	public ModelAndView manageMachine(HttpSession session) {
		service = new machineService();
		mav = new ModelAndView("/admin/machine/manageMachine");
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, "Liên hệ quản trị hệ thống để được hỗ trợ");
			return mav;
		}
		dataList = service.getAllMachine();
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/addMachine" }, method = RequestMethod.GET)
	public ModelAndView addMachineView(HttpSession session) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, "Liên hệ quản trị hệ thống để được hỗ trợ");
			return mav;
		}
		dataSelected = new machineEntity();
		service = new machineService();
		mav = new ModelAndView("/admin/machine/addMachine");
		map.addAttribute("isInsert", 1);
//		dataList = service.findDaList(0L, new machineEntity());
//		addAttribute();
		isInsert = true;
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/editMachine" }, method = RequestMethod.GET)
	public ModelAndView editMachineView(HttpSession session, @RequestParam("machineId") String machineId) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);

		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if (machineId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new machineService();
		mav = new ModelAndView("/admin/machine/addMachine");
		dataSelected = service.getDetail(machineId);
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/addNewMachine" }, method = RequestMethod.POST)
	public ModelAndView addNewMachine(@ModelAttribute("dataInsert") machineEntity dataInsert, HttpSession session,
			HttpServletRequest request) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new machineService();
		mav = new ModelAndView("/admin/machine/addMachine");

		if (service.insertOrUpdate(user.getUserId(), dataInsert)) {
			message = "Cập nhật thành công!";
		} else {
			message = AppConstants.MESSAGE_ERROR;
		}
		mav = new ModelAndView("/admin/product/addProduct");
		session.setAttribute(AppConstants.SESSION_MESSAGE, message);
		dataSelected = service.getDetail(dataInsert.getMachineId());
//		dataList = service.findDaList(0L, new productEntity());
		isInsert = false;
		logger.info(mav);
		return this.editMachineView(session, dataInsert.getMachineId());
//		return mav;
	}

	@RequestMapping(value = { "/admin/deleteMachine" }, method = RequestMethod.GET)
	public ModelAndView deleteMachine(HttpSession session, @RequestParam("machineId") String machineId) {

		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if (machineId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new machineService();
		mav = new ModelAndView("/admin/machine/manageMachine");
		dataSelected = service.getDetail(machineId);
		if (!service.delete(user.getUserId(), machineId)) {
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		session.setAttribute(AppConstants.SESSION_MESSAGE, "Xóa thành công");
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return manageMachine(session);
	}

	@ModelAttribute("dataInsert")
	public machineEntity dataInsert() {
		return new machineEntity();
	}
}
