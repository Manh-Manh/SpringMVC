package com.manhdn.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.entity.orderEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.orderService;
import com.manhdn.service.supplierService;
import com.manhdn.service.userService;

@Controller
public class userController extends CommonController<userEntity> {

	@Autowired
	userService service;

		
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageUser" }, method = RequestMethod.GET)
	public ModelAndView manageUser() {
		service = new userService();
		mav = new ModelAndView("/admin/user/manageUser");
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}

	@RequestMapping(value = { "/app-view/login" }, method = RequestMethod.GET)
	public ModelAndView login(HttpSession session) {
		mav = new ModelAndView("/user/login");
		mav.addObject(AppConstants.SESSION_USER, new userEntity());
		return mav;
	}

	@RequestMapping(value = { "/app-view/login" }, method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("userSearch") userEntity dataSearch, HttpSession session) {
		this.dataSearch = dataSearch;
		userEntity user = service.login(this.dataSearch);
		if (user != null) {
			session.setAttribute(AppConstants.SESSION_USER, user);
			orderEntity cart = this.findCart(user.getUserId());
			
			if(cart != null) {
				session.setAttribute(AppConstants.SESSION_CART, cart);
			}
		}
		message = "Đăng nhập thành công!";
		mav = new ModelAndView("redirect:/app-view/home-page");
//		return "redirect:/showUser/" ;
		addData();
		return mav;
	}
	
	@RequestMapping(value = { "/app-view/register" }, method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("userRegister") userEntity dataSearch, HttpSession session) {
		this.dataSearch = dataSearch;
		boolean i = service.register(this.dataSearch);
		if(i) {
			userEntity user = service.login(this.dataSearch);
			if (user != null) {
				session.setAttribute(AppConstants.SESSION_USER, user);
				
				orderEntity cart = this.findCart(user.getUserId());
				if (cart != null) {
					session.setAttribute(AppConstants.SESSION_CART, cart);
				} else {
					session.setAttribute(AppConstants.SESSION_CART, new orderEntity());
				}
			}
			mav = new ModelAndView("redirect:/app-view/home-page");
			message = "Đăng kí thành công!";
			addData();
			return mav;

		} else {
			mav = new ModelAndView("/user/login");
			message = "Đăng kí thất bại!";
			addData();
			return mav;
		}


	}
	
	@RequestMapping(value = { "/app-view/logout" }, method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		
		session.removeAttribute("user");
		mav = new ModelAndView("redirect:/app-view/");
		addData();
		return mav;
	}

	@RequestMapping(value = { "/app-view/myAccount" }, method = RequestMethod.GET)
	public ModelAndView myAccount(HttpSession session,HttpServletResponse res ) {
		res.setCharacterEncoding("UTF-8");
		message="";
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null) {
			mav = new ModelAndView("redirect:/app-view/login");
			addData();
			return mav;
		}
		List<orderEntity> listOrders =new ArrayList<orderEntity>();
		orderService orderS = new orderService();
		listOrders = (List<orderEntity>) orderS.findOderByUserId(user.getUserId(), null);
		map.addAttribute("listOrders", listOrders);
		
		mav = new ModelAndView("/user/myAccount");
		addData();
		return mav;
	}

	@RequestMapping(value = { "/app-view/updateUser" }, method = RequestMethod.POST)
	public ModelAndView myAccount(HttpServletRequest request, HttpSession session,
			@ModelAttribute("userUpdate") userEntity userUpdate) {

		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null || userUpdate == null) {
			mav = new ModelAndView("redirect:/app-view/login");
			addData();
			return mav;
		}
		userUpdate.setPassword(user.getPassword());
		//upload anh
		if(userUpdate.getFileAvatar() != null) {
			String upload = doUpload(request, userUpdate);
			userUpdate.setAvatar(userUpdate.getFileAvatar()[0].getOriginalFilename());
		}else {
			userUpdate.setAvatar(user.getAvatar()!=null?user.getAvatar():"");
		}
		service = new userService();
		boolean result = service.insertOrUpdate(user.getUserId(), userUpdate);
		if(result) {
			message = "Cập nhật thành công!";
			session.setAttribute(AppConstants.SESSION_USER, userUpdate);
		}
		mav = new ModelAndView("redirect:/app-view/myAccount");
		addData();
		return mav;
	}	
	
	@RequestMapping(value = { "/app-view/changePassword" }, method = RequestMethod.POST)
	public ModelAndView changePassword(HttpServletRequest request, HttpSession session,
			@ModelAttribute("userUpdate") userEntity userUpdate) {
		mav = new ModelAndView("redirect:/app-view/myAccount");
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null || userUpdate == null) {
			mav = new ModelAndView("redirect:/app-view/login");
			addData();
			return mav;
		}
		if(!userUpdate.getPassword().equals(user.getPassword())) {
//			userUpdate.setPassword(userUpdate.getNewPassword());
			message = "Mật khẩu không đúng!";
			session.setAttribute(AppConstants.SESSION_MESSAGE, message);
			session.setAttribute(AppConstants.SESSION_USER, user);
			return mav;
		}
		user.setPassword(userUpdate.getNewPassword());
		service = new userService();
		boolean result = service.insertOrUpdate(user.getUserId(), user);
		if(result) {
			message = "Cập nhật thành công!";

			session.setAttribute(AppConstants.SESSION_MESSAGE, message);
			session.setAttribute(AppConstants.SESSION_USER, user);
		}
		mav = new ModelAndView("redirect:/app-view/myAccount");
		addData();
		return mav;
	}	

	private String doUpload(HttpServletRequest request, userEntity userUpload) {

		

		// Thư mục gốc upload file.
		String uploadRootPath = request.getServletContext().getRealPath("upload");
//		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath);
		//
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		CommonsMultipartFile[] fileDatas = userUpload.getFileAvatar();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		for (CommonsMultipartFile fileData : fileDatas) {

			// Tên file gốc tại Client.
			String name = fileData.getOriginalFilename();
//			System.out.println("Client File Name = " + name);

			if (name != null && name.length() > 0) {
				try {
					// Tạo file tại Server.
					String urlDir = "\\..\\WEB-INF\\views\\assets\\images\\user";
					File serverFile = new File(uploadRootDir.getAbsolutePath()+ urlDir + File.separator + name);

					// Luồng ghi dữ liệu vào file trên Server.
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					// Tao file vao project
					urlDir = "D:\\java\\web\\SpringMVC\\src\\main\\webapp\\WEB-INF\\views\\assets\\images\\user";
					serverFile = new File( urlDir + File.separator + name);

					// Luồng ghi dữ liệu vào file trên Server.
					stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					uploadedFiles.add(serverFile);
//					System.out.println("Write file: " + serverFile);
				} catch (Exception e) {
					System.out.println("Error Write file: " + name);
					return "Fail";
				}
			}
		}
		
		return "Success";
	}
	
	private orderEntity findCart(Long userId) {
		orderService orderS = new orderService();
		List<orderEntity> lst = orderS.findOderByUserId(userId, AppConstants.OS_NO_ORDER);
		orderEntity cart = null;
		if (lst != null) {
			cart = lst.get(0);
		}
		return cart;
	}
	@ModelAttribute("userSearch")
	public userEntity userSearch() {
		return new userEntity();
	}

	@ModelAttribute("userRegister")
	public userEntity userRegister() {
		return new userEntity();
	}
	
	
	@ModelAttribute("prodSelected")
	public productEntity prodSelected() {
		return new productEntity();
	}
}
