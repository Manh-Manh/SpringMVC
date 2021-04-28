package com.manhdn.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.entity.ajaxEntity;
import com.manhdn.entity.orderEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.supplierService;
import com.manhdn.service.userService;
import com.manhdn.service.orderService;
import com.manhdn.service.productService;

@Controller
public class orderController extends CommonController<orderEntity> {

	@Autowired
	orderService service;
	Logger logger = Logger.getLogger(orderController.class);
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageOrder" }, method = RequestMethod.GET)
	public ModelAndView manageOrder() {
		service = new orderService();
		mav = new ModelAndView("/admin/order/manageOrder");
		dataList = service.findDaList(0L, null);
		addData();
		logger.info(mav);
		return mav;
	}

	/**
	 * xem gio hang
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = { "/app-view/cart" }, method = RequestMethod.GET)
	public ModelAndView viewCart(HttpSession session) {
		mav = new ModelAndView("/user/cart");
		message = "";
		orderEntity cart = (orderEntity) session.getAttribute(AppConstants.SESSION_CART);
		if (cart == null || cart.getListProduct().size() == 0) {
			cart = new orderEntity();
			session.setAttribute(AppConstants.SESSION_CART, cart);
			mav = new ModelAndView("/user/emptyCart");
		}
		addData();
		logger.info(mav);
		return mav;
	}

	/**
	 * xem don hang
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = { "/app-view/viewOrder" }, method = RequestMethod.GET)
	public ModelAndView viewOrder(@RequestParam("orderId") String orderId) {
		mav = new ModelAndView("/user/viewOrder");
		dataSelected = service.findOrderById(orderId);
		if (dataSelected == null || dataSelected.getListProduct().size() == 0) {
			mav = new ModelAndView("/user/emptyCart");
		}
		addData();
		logger.info(mav);
		return mav;
	}

	/**
	 * Huy don hang
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = { "/app-view/cancelOrder" }, method = RequestMethod.GET)
	public ModelAndView cancelOrder(HttpSession session, @RequestParam("orderId") String orderId) {
		mav = new ModelAndView("/user/myAccount");
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user == null || user.getUserId() == null) {
			mav = new ModelAndView("redirect:/app-view/login");
			logger.info(mav);
			return mav;
		}
		dataSelected = service.findOrderById(orderId);
		dataSelected.setStatus(AppConstants.OS_ORDER_CANCEL);
		boolean s = service.insertOrUpdate(user.getUserId(), dataSelected, AppConstants.OS_ORDER_CANCEL);
		if (s) {
			message = "Hủy đơn hàng thành công!";
			addMessage(message, session);

		} else {
			message = "Hủy đơn hàng thất bại!";
			addMessage(message, session);

		}
		mav = new ModelAndView("redirect:/app-view/myAccount");
		addData();
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = { "/app-view/addToCart" }, method = RequestMethod.POST)
	public ModelAndView addToCart(@ModelAttribute("prodSelected") productEntity product, HttpSession session) {
		mav = new ModelAndView("/user/cart");
		orderEntity cart = (orderEntity) session.getAttribute("cart");
		if (cart == null) {
			cart = new orderEntity();
			session.setAttribute(AppConstants.SESSION_CART, cart);
		}
		// cap nhat vao session
		Long quantity = 1L;
		cart = addCart(product.getProductId(), product.getCartQuantity(), cart);
		// cap nhat vao db
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user != null && user.getUserId() != null) {
			cart.setUserId(user.getUserId());
			boolean i = service.insertOrUpdate(user.getUserId(), cart, AppConstants.OS_NO_ORDER);
		}
		logger.info(mav);
		return mav;

	}

	@RequestMapping(value = { "/app-view/addToCart" }, method = RequestMethod.GET)
	public ModelAndView addToCart(@RequestParam("id") String id, HttpSession session) {
		mav = new ModelAndView("redirect:/app-view/cart");
		orderEntity cart = (orderEntity) session.getAttribute(AppConstants.SESSION_CART);
		if (cart == null) {
			cart = new orderEntity();
			session.setAttribute(AppConstants.SESSION_CART, cart);
		}
		// cap nhat vao session
		Long quantity = 1L;
		cart = addCart(id, quantity, cart);
		// cap nhat vao db
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user != null && user.getUserId() != null) {
			cart.setUserId(user.getUserId());
			boolean i = service.insertOrUpdate(user.getUserId(), cart, AppConstants.OS_NO_ORDER);
		}
		logger.info(mav);
		return mav;
	}

	@RequestMapping(value = {
			"/app-view/updateCart" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateCart(@RequestBody String data, HttpSession session) {
		service = new orderService();
		// lay du lieu tu client
		Gson gson = new GsonBuilder().create();
		List<ajaxEntity> lstSearch = new ArrayList<ajaxEntity>();
		JsonParser parser = new JsonParser();
		JsonElement tradeElement = parser.parse(data);
		JsonArray arr = tradeElement.getAsJsonArray();
		for (int i = 0; i < arr.size(); i++) {
			ajaxEntity o = (ajaxEntity) gson.fromJson(arr.get(i), ajaxEntity.class);
			lstSearch.add(o);
		}

		for (ajaxEntity e : lstSearch) {
			if (!FunctionCommon.isEmpty(e.getValue())) {
				mapSearch.put(e.getName(), e.getValue());
			} else if (FunctionCommon.isEmpty(e.getValue())) {
				mapSearch.put(e.getName(), new ArrayList<String>());
			}
		}
		// cap nhat vao session
		orderEntity cart = (orderEntity) session.getAttribute(AppConstants.SESSION_CART);
		if (cart == null) {
			cart = new orderEntity();
			session.setAttribute(AppConstants.SESSION_CART, cart);
		}
		for (int i = 0; i < mapSearch.get(AppConstants.MAP_SEARCH_PRODUCT_ID).size(); i++) {
			String id = mapSearch.get(AppConstants.MAP_SEARCH_PRODUCT_ID).get(i);
			Long quatity = Long.parseLong(mapSearch.get(AppConstants.MAP_SEARCH_CART_QUANTITY).get(i));
			cart = updateCart(id, quatity, cart);
		}

		// cap nhat vao db
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if (user != null && user.getUserId() != null) {
			cart.setUserId(user.getUserId());
			boolean i = service.insertOrUpdate(user.getUserId(), cart, AppConstants.OS_NO_ORDER);
		}

		addData();
		return "updateCart";
	}

	@RequestMapping(value = { "/app-view/checkOut" }, method = RequestMethod.GET)
	public ModelAndView checkOut(HttpSession session, HttpServletResponse res) {
		res.setCharacterEncoding("UTF-8");
		mav = new ModelAndView("/user/checkOut");
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		String message = "";

		if (user == null || user.getUserId() == null) {
			ModelAndView m = new ModelAndView("redirect:/app-view/login");
			return m;
		}

		addData();
		logger.info(mav);
		return mav;
	}

	// ư
	@RequestMapping(value = { "/app-view/checkOut" }, method = RequestMethod.POST)
	public ModelAndView doCheckOut(@ModelAttribute("userCheckOut") userEntity userCheckOut, HttpSession session) {
		mav = new ModelAndView("/user/checkOut");
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		message = "";
		if (user == null || user.getUserId() == null) {
			ModelAndView m = new ModelAndView("redirect:/app-view/login");
			return m;
		}

		// update user
		if (!user.getAddress().equals(userCheckOut.getAddress())
				|| !user.getPhoneNumber().equals(userCheckOut.getPhoneNumber())) {
			user.setAddress(userCheckOut.getAddress());
			user.setPhoneNumber(userCheckOut.getPhoneNumber());
			userService userS = new userService();
			boolean updateUser = userS.insertOrUpdate(null, user);
			if (updateUser == false) {
				message = "Đặt hàng không thành công!";
				addMessage(message, session);
				logger.info(mav);
				return mav;
			}
		}

		// update cart
		orderEntity cart = (orderEntity) session.getAttribute(AppConstants.SESSION_CART);
		orderService orderS = new orderService();
		boolean result = orderS.insertOrUpdate(user.getUserId(), cart, AppConstants.OS_ORDERED);
		if (!result) {
			message = "Đặt hàng không thành công!";
			addMessage(message, session);
			addData();
			logger.info(mav);
			return mav;
		}
		mav = new ModelAndView("redirect:/app-view/homePage");
		message = "Đặt hàng thành công!";
		addMessage(message, session);
		cart.getListProduct().clear();
		addData();
		logger.info(mav);
		return mav;

	}

	// update
	private orderEntity updateCart(String id, Long cartQuantity, orderEntity data) {
		orderEntity result = data;
		for (productEntity p : result.getListProduct()) {
			if (p.getProductId().equals(id)) {
				if (cartQuantity == 0) { // Xoa
					result.getListProduct().remove(p);
					return result;
				} else {
					p.setCartQuantity(cartQuantity);
					return result;
				}
			}
		}
		return result;
	}

	// add
	private orderEntity addCart(String id, Long cartQuantity, orderEntity data) {
		orderEntity result = data;
		for (productEntity p : result.getListProduct()) {
			if (p.getProductId().equals(id)) { // da co trong cart
				p.setCartQuantity(cartQuantity + p.getCartQuantity());
				return result;
			}
		}
		// Chua co trong cart
		productService proS = new productService();
		productEntity p = proS.getProductDetail(id);
		p.setCartQuantity(cartQuantity);
		result.getListProduct().add(p);
		return result;
	}

	@ModelAttribute("userCheckOut")
	public userEntity userCheckout() {
		return new userEntity();
	}

	private void addAttribute() {
		super.addData();
	}
}
