package com.manhdn.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.dao.productDAO;
import com.manhdn.entity.productEntity;
import com.manhdn.service.productService;

@Controller
@Component
public class productController extends CommonController<productEntity>{
	
	@Autowired
	productService service;
	@RequestMapping( value = { "/app-view","/app-view/homePage"}, method = RequestMethod.GET)
	public ModelAndView home() {
		service = new productService();
		mav = new ModelAndView("/user/home");
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);;
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}
	/**
	 * @author manhnd
	 * Xem chi tiet
	 * @param id
	 */
	@RequestMapping(value = {"/app-view/viewDetail"}, method = RequestMethod.GET)
	public ModelAndView viewDetail(@RequestParam("id") String id) {
		mav = new ModelAndView("/user/viewDetail");
		service = new productService();
		dataSelected = service.getProductDetail(id);
//		smav.addObject("dataSelected", dataSelected);
		addData();
		return mav;
	}
	
	@RequestMapping(value = {"/app-view/cart"}, method = RequestMethod.GET)
	public ModelAndView cart() {
		mav = new ModelAndView("/user/cart");
//		service = new productService();
//		dataSelected = service.getProductDetail(id);
//		smav.addObject("dataSelected", dataSelected);
		addData();
		return mav;
	}
	/**
	 * QLSP
	 * @return
	 */
	@RequestMapping( value = { "/admin/manageProduct"}, method = RequestMethod.GET)
	public ModelAndView manageProduct() {
		service = new productService();
		mav = new ModelAndView("/admin/product/manageProduct");
		dataList = service.findDaList(0L, null);
//		mav.addObject("dataList",dataList);;
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping( value = { "/admin/addProduct"}, method = RequestMethod.GET)
	public ModelAndView addProductView() {
		service = new productService();
		mav = new ModelAndView("/admin/product/addProduct");
		dataList = service.findDaList(0L, null);
		addData();
		return mav;
	}
	
	@RequestMapping( value = { "/admin/addProduct"}, method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("dataInsert") productEntity dataInsert, HttpSession session) {
		service = new productService();
		mav = new ModelAndView("/admin/product/addProduct");
		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}
	
	@ModelAttribute("dataInsert")
	productEntity dataIn() {
		return new productEntity();
	}
}
