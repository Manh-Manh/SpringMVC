package com.manhdn.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.entity.commentEntity;
import com.manhdn.entity.discountEntity;
import com.manhdn.entity.orderEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.statisticProductEntity;
import com.manhdn.entity.supplierEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.commentService;
import com.manhdn.service.discountService;
import com.manhdn.service.orderService;
import com.manhdn.service.statisticProductService;
import com.manhdn.service.supplierService;
import com.manhdn.service.userService;
import com.manhdn.social.GooglePojo;
import com.manhdn.social.GoogleUtils;
import com.mysql.cj.log.Log;
import javax.imageio.*;

@Controller
public class userController extends CommonController<userEntity> {

	@Autowired
	userService service;
	private Logger logger = Logger.getLogger(userController.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageUser" }, method = RequestMethod.GET)
	public ModelAndView manageUser(HttpSession session) {
		service = new userService();
		mav = new ModelAndView("/admin/user/manageUser");
		if(!isAdmin(session)) {
			user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
			if(!user.getListRoleName().contains(AppConstants.ROLE_ADMIN)) {
				mav = new ModelAndView("redirect:/app-view");
				logger.error("Khong co quyen");
				session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
				return mav;
			}
		}
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		logger.info(mav);
		return mav;
	}
	@RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		service = new userService();
		ModelAndView mav = new ModelAndView("/admin/home");
		if (!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		
		orderService orderS = new orderService();
		// Danh sach cac hoa don moi trong thang
		List<orderEntity> listNewOrder = orderS.getListNewOrder();
		map.addAttribute("listNewOrder", listNewOrder);
		// Doanh so
		statisticProductService statisS = new statisticProductService();
		List<statisticProductEntity> listStatisticProduct = statisS.findDaListByThisMonth(user.getUserId());
		Long saleTotal=0L;
		if(!FunctionCommon.isEmpty(listStatisticProduct)) {
			for(statisticProductEntity sta: listStatisticProduct) {
				saleTotal += sta.getTotal();
			}
		}
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		String saleCost =  myFormatter.format(saleTotal);
		map.addAttribute("saleCost", saleCost);
		// danh sach giam gia
		discountService disS = new discountService();
		List<discountEntity> lstDiscount = disS.findDaList(user.getUserId(), null);
		
		map.addAttribute("listDiscount", lstDiscount);
		addData();
		mav.addAllObjects(map);
		return mav;
	}
	@RequestMapping(value = { "/app-view/login" }, method = RequestMethod.GET)
	public ModelAndView login(HttpSession session, HttpServletRequest req) {
		mav = new ModelAndView("/user/login");
		mav.addObject(AppConstants.SESSION_USER, new userEntity());
		logger.info(mav);
		return mav;
	}
	@RequestMapping(value = { "/app-view/contact" }, method = RequestMethod.GET)
	public ModelAndView contact(HttpSession session) {
		mav = new ModelAndView("/user/contact");
		mav.addObject(AppConstants.SESSION_USER, new userEntity());
		logger.info(mav);
		return mav;
	}
	
	@RequestMapping(value = { "/app-view/about" }, method = RequestMethod.GET)
	public ModelAndView about(HttpSession session) {
		mav = new ModelAndView("/user/about");
		mav.addObject(AppConstants.SESSION_USER, new userEntity());
		logger.info(mav);
		return mav;
	}
	@RequestMapping(value = { "/app-view/login" }, method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("userSearch") userEntity dataSearch, HttpSession session) {
		this.dataSearch = dataSearch;
		userEntity user = service.login(this.dataSearch);
		if(user == null) {
			mav = new ModelAndView("/user/login");
			session.setAttribute(AppConstants.SESSION_MESSAGE, "Thông tin tài khoản không đúng!");
			return mav;
		}
		if (user != null) {
			session.setAttribute(AppConstants.SESSION_USER, user);
			orderEntity cart = this.findCart(user.getUserId());

			if (cart != null) {
				session.setAttribute(AppConstants.SESSION_CART, cart);
			}
		}
//		message = "Đăng nhập thành công!";
		if(isAdmin(session)) {
			mav = new ModelAndView("redirect:/admin");
			return mav;
		}
		mav = new ModelAndView("redirect:/app-view/home-page");
//		return "redirect:/showUser/" ;
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/app-view/register" }, method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("userRegister") userEntity dataSearch, HttpSession session) {
		this.dataSearch = dataSearch;
		boolean i = service.register(this.dataSearch);
		if (i) {
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
			logger.info(mav);
			return mav;

		} else {
			mav = new ModelAndView("/user/login");
			message = "Đăng kí thất bại!";
			addData();
			logger.info(mav);
			return mav;
		}

	}

	@RequestMapping(value = { "/app-view/logout" }, method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute(AppConstants.SESSION_CART);
		session.removeAttribute(AppConstants.SESSION_USER);
		mav = new ModelAndView("redirect:/app-view/");
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/app-view/myAccount" }, method = RequestMethod.GET)
	public ModelAndView myAccount(HttpSession session, HttpServletResponse res) {
		res.setCharacterEncoding("UTF-8");
		message = "";
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null) {
			mav = new ModelAndView("redirect:/app-view/login");
			addData();
			logger.info(mav);
			return mav;
		}
		List<orderEntity> listOrders = new ArrayList<orderEntity>();
		orderService orderS = new orderService();
		listOrders = (List<orderEntity>) orderS.findOderByUserId(user.getUserId(), null);
		map.addAttribute("listOrders", listOrders);

		mav = new ModelAndView("/user/myAccount");
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/app-view/updateUser" }, method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, HttpSession session,
			@ModelAttribute("userUpdate") userEntity userUpdate) {

		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null || userUpdate == null) {
			mav = new ModelAndView("redirect:/app-view/login");
			addData();
			logger.info(mav);
			return mav;
		}
		userUpdate.setUserId(user.getUserId());
		userUpdate.setUserName(user.getUserName());
		userUpdate.setPassword(user.getPassword());
		// upload anh
		if (userUpdate.getFileAvatar() != null && userUpdate.getFileAvatar()[0].getSize() >0 ) {
			String upload = doUpload(request, userUpdate);
			userUpdate.setAvatar(userUpdate.getFileAvatar()[0].getOriginalFilename());
		} else {
			userUpdate.setAvatar(user.getAvatar() != null ? user.getAvatar() : "");
		}
		service = new userService();
		boolean result = service.insertOrUpdate(user.getUserId(), userUpdate);
		if (result) {
			message = "Cập nhật thành công!";
			session.setAttribute(AppConstants.SESSION_MESSAGE, message);
			session.setAttribute(AppConstants.SESSION_USER, userUpdate);
		}
		mav = new ModelAndView("redirect:/app-view/myAccount");
		addData();
		logger.info(mav);
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
			logger.info(mav);
			return mav;
		}
		if (!userUpdate.getPassword().equals(user.getPassword())) {
//			userUpdate.setPassword(userUpdate.getNewPassword());
			message = "Mật khẩu không đúng!";
			session.setAttribute(AppConstants.SESSION_MESSAGE, message);
			session.setAttribute(AppConstants.SESSION_USER, user);
			logger.info(mav);
			return mav;
		}
		user.setPassword(userUpdate.getNewPassword());
		service = new userService();
		boolean result = service.insertOrUpdate(user.getUserId(), user);
		if (result) {
			message = "Cập nhật thành công!";
			session.setAttribute(AppConstants.SESSION_MESSAGE, message);
			session.setAttribute(AppConstants.SESSION_USER, user);
		}
		mav = new ModelAndView("redirect:/app-view/myAccount");
		addData();
		logger.info(mav);
		return mav;
	}

	/**
	 * commet
	 * 
	 * @param session
	 * @param cmt
	 * @return
	 */
	@RequestMapping(value = { "/app-view/comment", "/app-view/replyComment" }, method = RequestMethod.POST)
	public ModelAndView doComment(HttpSession session, @ModelAttribute("comment") commentEntity cmt) {
		if (cmt == null || cmt.getProductId() == null) {
			mav = new ModelAndView("/app-view/");
			message = "Liên hệ quản trị hệ thống để được hỗ trợ!";
			addMessage(message, session);
			logger.info(mav);
			return mav;
		}
		mav = new ModelAndView("/app-view/viewDetail?id=" + cmt.getProductId());
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null) {
			mav = new ModelAndView("redirect:/app-view/login");
			addData();
			logger.info(mav);
			return mav;
		}
		commentService cmtS = new commentService();
		boolean i = cmtS.insertOrUpdate(cmt.getUserId(), cmt);
		if (i) {
			message = "Cảm ơn bạn đã đánh giá sản phẩm!";
			addMessage(message, session);
		} else {
			message = "Liên hệ quản trị hệ thống để được hỗ trợ!";
			addMessage(message, session);
		}
		mav = new ModelAndView("redirect:/app-view/viewDetail?id=" + cmt.getProductId());
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/admin/addUser" }, method = RequestMethod.GET)
	public ModelAndView addUserView(HttpSession session) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
//		if(isInsert) {
			dataSelected = new userEntity();
//		}
		service = new userService();
		mav = new ModelAndView("/admin/user/addUser");
		map.addAttribute("isInsert", true);
//		dataList = service.findDaList(0L, new userEntity());
//		addAttribute();
		isInsert = true;
		addData();
		logger.info(mav);
		return mav;
	}
	@RequestMapping(value = { "/admin/editUser" }, method = RequestMethod.GET)
	public ModelAndView editUserView(HttpSession session, @RequestParam("userId") Long userId) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if(userId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new userService();
		mav = new ModelAndView("/admin/user/addUser");
		dataSelected = service.getDetail(userId);
//		if(isInsert) {
//			dataSelected = new userEntity();
//		}
		isInsert = false;
		map.addAttribute("isInsert", false);
		addData();
		logger.info(mav);
		return mav;
	}
	
	/**
	 * delete
	 * @param session
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = { "/admin/deleteUser" }, method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpSession session, @RequestParam("userId") Long userId) {
		
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if(userId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new userService();
		mav = new ModelAndView("/admin/user/manageUser");
		dataSelected = service.getDetail(userId);
		if(!service.delete(user.getUserId(), userId)) {
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		session.setAttribute(AppConstants.SESSION_MESSAGE,"Xóa thành công");
		map.addAttribute("isInsert", false);
		addData();
		logger.info(mav);
		return manageUser(session);
	}

	@RequestMapping(value = { "/admin/addNewUser" }, method = RequestMethod.POST)
	public ModelAndView addNewUser(@ModelAttribute("dataInsert") userEntity dataInsert, HttpSession session,
			HttpServletRequest request) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new userService();
		mav = new ModelAndView("/admin/user/addUser");
		
		if(service.insertOrUpdate(0L, dataInsert)) {
			message = "Cập nhật thành công!";
		}else {
			message= AppConstants.MESSAGE_ERROR;
		}
		mav = new ModelAndView("/admin/product/addProduct");
		session.setAttribute(AppConstants.SESSION_MESSAGE, message);
		dataSelected = service.getDetail(dataInsert.getUserId());
		isInsert = false;
		logger.info(mav);
		return this.editUserView(session, dataInsert.getUserId());
//		return mav;
	}
//	@RequestMapping(value = { "/app-view/replyComment" }, method = RequestMethod.POST)
//	public ModelAndView doReplyComment( HttpSession session,
//			@ModelAttribute("comment") commentEntity cmt) {
//		if(cmt==null ||cmt.getProductId()==null) {
//			mav = new ModelAndView("/app-view/");
//			message = "Liên hệ quản trị hệ thống để được hỗ trợ!";
//			addMessage(message, session);
//			return mav;
//		}
//		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
//		if (user == null) {
//			mav = new ModelAndView("redirect:/app-view/login");
//			addData();
//			return mav;
//		}
//		commentService cmtS = new commentService();
//		boolean i = cmtS.insertOrUpdate(cmt.getUserId(), cmt);
//		if(i) {
//			message = "Cảm ơn bạn đã đánh giá sản phẩm!";
//			addMessage(message, session);
//		}else {
//			message = "Liên hệ quản trị hệ thống để được hỗ trợ!";
//			addMessage(message, session);
//		}
//		mav = new ModelAndView("redirect:/app-view/viewDetail?id="+cmt.getProductId());
//		addData();
//		return mav;
//	}	
	private String doUpload(HttpServletRequest request, userEntity userUpload) {

		// Thư mục gốc upload file.
		String uploadRootPath = request.getServletContext().getRealPath("upload");
//		System.out.println("uploadRootPath=" + uploadRootPath);
		uploadRootPath = uploadRootPath+"\\"+userUpload.getUserId();
		File uploadRootDir = new File(uploadRootPath);
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
					String urlDir = "\\..\\..\\WEB-INF\\views\\assets\\images\\user\\"+userUpload.getUserId();
					File uploadDir = new File(uploadRootDir.getAbsolutePath() + urlDir);
					// Tạo thư mục upload nếu nó không tồn tại.
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}
					File serverFile = new File(uploadRootDir.getAbsolutePath() + urlDir + File.separator + name);
					// Luồng ghi dữ liệu vào file trên Server.
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					// Tao file vao project
					urlDir = "D:\\java\\web\\SpringMVC\\src\\main\\webapp\\WEB-INF\\views\\assets\\images\\user\\"+userUpload.getUserId();
					uploadDir = new File(urlDir);
					// Tạo thư mục gốc upload nếu nó không tồn tại.
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}
					serverFile = new File(urlDir + File.separator + name);
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

	private boolean dowloadImgFromGG(HttpServletRequest request, userEntity user) throws IOException {
		
		String uploadRootPath = request.getServletContext().getRealPath("upload");
//		System.out.println("uploadRootPath=" + uploadRootPath);
		uploadRootPath = uploadRootPath+"\\"+user.getUserId();
		File uploadRootDir = new File(uploadRootPath);
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		URL url = new URL(user.getPath());
		InputStream in = new BufferedInputStream(url.openStream());
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		{
		   out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();
		// Upload server
		String urlDir = "\\..\\..\\WEB-INF\\views\\assets\\images\\user\\"+user.getUserId();
		File uploadDir = new File(uploadRootDir.getAbsolutePath() + urlDir);
		// Tạo thư mục upload nếu nó không tồn tại.
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
//		String []img = user.getAvatar().split("\\");
//		String imgName = img[img.length-1]+".png";
		String imgName = user.getAvatar();
		File serverFile = new File(uploadRootDir.getAbsolutePath() + urlDir + File.separator + imgName);
		FileOutputStream fos = new FileOutputStream(serverFile);
		fos.write(response);
		fos.close();
		// Tao file vao project
		urlDir = "D:\\java\\web\\SpringMVC\\src\\main\\webapp\\WEB-INF\\views\\assets\\images\\user\\"+user.getUserId();
		uploadDir = new File(urlDir);
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		serverFile = new File(urlDir + File.separator + imgName);
		fos = new FileOutputStream(serverFile );
		fos.write(response);
		fos.close();
		return true;
	}
	@RequestMapping("/app-view/login-google")
	public ModelAndView loginGoogle(HttpServletRequest request, HttpSession session) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");
		mav = new ModelAndView("redirect:/app-view/home-page");
		if (code == null || code.isEmpty()) {
			return new ModelAndView("redirect:/app-view/login");
		}
		GoogleUtils googleUtils = new GoogleUtils();
		String accessToken = googleUtils.getToken(code);
		
		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
//		UserDetails userDetail = googleUtils.buildUser(googlePojo);
		userEntity user = googleUtils.toUserEntity(googlePojo);
		user.setPassword(accessToken);
		userEntity userTest = service.findUserByUserEmail(user.getEmail());
		// Chua co trong db
		if(userTest == null) {
			
			if(service.insertOrUpdate(null, user)) {
				userTest = service.findUserByUserEmail(user.getEmail());
				// up anh
				user.setUserId(userTest.getUserId());
				dowloadImgFromGG(request, user);
			}
			else {
				message = AppConstants.MESSAGE_ERROR;
				session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
				return new ModelAndView("redirect:/app-view/login");
			}
		}
		
		if (userTest != null) {
			session.setAttribute(AppConstants.SESSION_USER, userTest);
			orderEntity cart = this.findCart(user.getUserId());
			if (cart != null) {
				session.setAttribute(AppConstants.SESSION_CART, cart);
			}
		}
		
		mav = new ModelAndView("redirect:/app-view/home-page");
//		return "redirect:/showUser/" ;
		addData();
		logger.info(mav);
		return mav;
		
		
		
		
		
		
		
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
//				userDetail.getAuthorities());
//		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		return "redirect:/app-view";
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
	@ModelAttribute("dataInsert")
	public userEntity daInsert() {
		return new userEntity();
	}
	@ModelAttribute("userRegister")
	public userEntity userRegister() {
		return new userEntity();
	}

	@ModelAttribute("comment")
	public commentEntity comment() {
		return new commentEntity();
	}

	@ModelAttribute("prodSelected")
	public productEntity prodSelected() {
		return new productEntity();
	}
}
