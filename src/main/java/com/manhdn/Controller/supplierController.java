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
import com.manhdn.entity.supplierEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.supplierService;
import com.manhdn.service.productService;

@Controller
public class supplierController extends CommonController<supplierEntity> {
	@Autowired
	supplierService service;
	private Logger logger = Logger.getLogger(supplierController.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageSupplier" }, method = RequestMethod.GET)
	public ModelAndView manageSupplier(HttpSession session) {
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new supplierService();
		mav = new ModelAndView("/admin/supplier/manageSupplier");
		dataList = service.getAllSupplier();
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		logger.info(mav);
		return mav;
	}
	

	@RequestMapping(value = { "/admin/addSupplier" }, method = RequestMethod.GET)
	public ModelAndView addSupplierView(HttpSession session) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if(isInsert) {
			dataSelected = new supplierEntity();
		}
		service = new supplierService();
		mav = new ModelAndView("/admin/supplier/addSupplier");
		map.addAttribute("isInsert", 1);
//		dataList = service.findDaList(0L, new supplierEntity());
//		addAttribute();
		isInsert = true;
		addData();
		logger.info(mav);
		return mav;
	}
	@RequestMapping(value = { "/admin/editSupplier" }, method = RequestMethod.GET)
	public ModelAndView editSupplierView(HttpSession session, @RequestParam("supplierId") String supplierId) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if(supplierId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new supplierService();
		mav = new ModelAndView("/admin/supplier/addSupplier");
		dataSelected = service.getDetail(supplierId);
		if(isInsert) {
			dataSelected = new supplierEntity();
		}
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return mav;
	}
	
	/**
	 * delete
	 * @param session
	 * @param supplierId
	 * @return
	 */
	@RequestMapping(value = { "/admin/deleteSupplier" }, method = RequestMethod.GET)
	public ModelAndView deleteSupplier(HttpSession session, @RequestParam("supplierId") String supplierId) {
		
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if(supplierId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new supplierService();
		mav = new ModelAndView("/admin/supplier/manageSupplier");
		dataSelected = service.getDetail(supplierId);
		if(!service.delete(user.getUserId(), supplierId)) {
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		session.setAttribute(AppConstants.SESSION_MESSAGE,"Xóa thành công");
		map.addAttribute("isInsert", 0);
		addData();
		logger.info(mav);
		return manageSupplier(session);
	}

	@RequestMapping(value = { "/admin/addNewSupplier" }, method = RequestMethod.POST)
	public ModelAndView addNewSupplier(@ModelAttribute("dataInsert") supplierEntity dataInsert, HttpSession session,
			HttpServletRequest request) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new supplierService();
		mav = new ModelAndView("/admin/supplier/addSupplier");
		
		// upload Anh
		if(dataInsert.getFileLogo() != null && dataInsert.getFileLogo().length >0 ) {
			if(!FunctionCommon.isEmpty(dataInsert.getFileLogo()[0].getOriginalFilename())) {
				boolean upload = this.doUpload(request, dataInsert);
				dataInsert.setLogo(dataInsert.getFileLogo()[0].getOriginalFilename());
			}
		}
		if(service.insertOrUpdate(0L, dataInsert)) {
			message = "Cập nhật thành công!";
		}else {
			message= AppConstants.MESSAGE_ERROR;
		}
		mav = new ModelAndView("/admin/product/addProduct");
		session.setAttribute(AppConstants.SESSION_MESSAGE, message);
		dataSelected = service.getDetail(dataInsert.getSupplierId());
//		dataList = service.findDaList(0L, new productEntity());
		isInsert = false;
		logger.info(mav);
		return this.editSupplierView(session, dataInsert.getSupplierId());
//		return mav;
	}
	
	
	private boolean doUpload(HttpServletRequest request, supplierEntity supUpload) {

		// Thư mục gốc upload file.
		String uploadRootPath = request.getServletContext().getRealPath("upload");
//		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath);
		//
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		CommonsMultipartFile[] fileDatas = supUpload.getFileLogo();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		for (CommonsMultipartFile fileData : fileDatas) {

			// Tên file gốc tại Client.
			String name = fileData.getOriginalFilename();
//			System.out.println("Client File Name = " + name);

			if (name != null && name.length() > 0) {
				try {
					// Tạo file tại Server.
					String urlDir = "\\..\\WEB-INF\\views\\assets\\images\\company-logo";
					File serverFile = new File(uploadRootDir.getAbsolutePath() + urlDir + File.separator + name);

					// Luồng ghi dữ liệu vào file trên Server.
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					// Tao file vao project
					urlDir = "D:\\java\\web\\SpringMVC\\src\\main\\webapp\\WEB-INF\\views\\assets\\images\\company-logo";
					serverFile = new File(urlDir + File.separator + name);

					// Luồng ghi dữ liệu vào file trên Server.
					stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					uploadedFiles.add(serverFile);
//					System.out.println("Write file: " + serverFile);
				} catch (Exception e) {
					System.out.println("Error Write file: " + name);
					return false;
				}
			}
		}

		return true;
	}
	@ModelAttribute("dataInsert")
	public supplierEntity dataInsert() {
		return new supplierEntity();
	}
}
