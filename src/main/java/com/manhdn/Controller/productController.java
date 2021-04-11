package com.manhdn.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
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
import com.manhdn.dao.productDAO;
import com.manhdn.entity.ajaxEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.service.productService;

@Controller
@Component
public class productController extends CommonController<productEntity> {

	@Autowired
	productService service;

	@RequestMapping(value = { "/app-view", "/app-view/homePage" }, method = RequestMethod.GET)
	public @ResponseBody ModelAndView home(@RequestParam(value = "page", required = false) Integer page) {
		service = new productService();
		mav = new ModelAndView("/user/home");
		if (isReload) {
			count = service.countDataList(0L, mapSearch);
			dataList = service.findDaList(0L, mapSearch, page, pageSize);
		}
//		mav.addObject("dataList",dataList);;
//		service.insertOrUpdate(0L, dataSearch);
//		mav.addObject("count", 150);
		if (page != null) {
			this.page = page;
		}

		addData();
		return mav;
	}

	/**
	 * @author manhnd Xem chi tiet
	 * @param id
	 */
	@RequestMapping(value = { "/app-view/viewDetail" }, method = RequestMethod.GET)
	public ModelAndView viewDetail(@RequestParam("id") String id) {
		mav = new ModelAndView("/user/viewDetail");
		service = new productService();
		dataSelected = service.getProductDetail(id);
//		smav.addObject("dataSelected", dataSelected);
		addData();
		return mav;
	}

	@RequestMapping(value = { "/app-view/cart" }, method = RequestMethod.GET)
	public ModelAndView cart() {
		mav = new ModelAndView("/user/cart");
//		service = new productService();
//		dataSelected = service.getProductDetail(id);
//		smav.addObject("dataSelected", dataSelected);
		addData();
		return mav;
	}

	/**
	 * 
	 * @param productSearch
	 * @param session
	 * @return
	 */
	@RequestMapping(value = { "/app-view/quickSearch" }, method = RequestMethod.POST)
	public ModelAndView quickSearch(@ModelAttribute("productSearch") productEntity productSearch, HttpSession session) {
		service = new productService();
		this.dataSearch = productSearch;
		this.isReload = false;
		type = "quickSearch";
		mav = new ModelAndView("redirect:/app-view/");
		dataList = service.quickSearch(0L, this.dataSearch.getProductName());
		addData();
		return mav;
	}

	@RequestMapping(value = {
			"/app-view/advSearch" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String advSearch(@RequestBody String data) {

		Gson gson = new GsonBuilder().create();
		List<ajaxEntity> lstSearch = new ArrayList<ajaxEntity>();
		JsonParser parser = new JsonParser();
		JsonElement tradeElement = parser.parse(data);
		JsonArray arr = tradeElement.getAsJsonArray();
		for (int i = 0; i < arr.size(); i++) {
			ajaxEntity o = (ajaxEntity) gson.fromJson(arr.get(i), ajaxEntity.class);
			lstSearch.add(o);
		}
		List<String> supId = new ArrayList<String>();
		// convert to Map

		for (ajaxEntity e : lstSearch) {
			if (!FunctionCommon.isEmpty(e.getValue())) {
				mapSearch.put(e.getName(), e.getValue());
			} else if (FunctionCommon.isEmpty(e.getValue())) {
				mapSearch.put(e.getName(), new ArrayList<String>());
			}
		}
		service = new productService();
		type = "advSearch";
//		this.dataSearch = productSearch;		
//		mav = new ModelAndView("/user/home");
		this.page = Integer.valueOf(mapSearch.get("page").get(0));
		if (FunctionCommon.isEmpty(mapSearch.get(AppConstants.MAP_SEARCH_SUPPLIER_ID))
				&& FunctionCommon.isEmpty(mapSearch.get(AppConstants.MAP_SEARCH_MACHINE_ID))
				&& FunctionCommon.isEmpty(mapSearch.get(AppConstants.MAP_SEARCH_STRAP_ID))) {
			this.type = "";
		}
		mapSearch.remove("page");
		count = service.countDataList(0L, mapSearch);
		dataList = service.findDaList(0L, mapSearch, page, pageSize);
		this.isReload = false;
		
		addData();
		return "advSearch";
	}

	/**
	 * QLSP
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageProduct" }, method = RequestMethod.GET)
	public ModelAndView manageProduct() {
		service = new productService();
		mav = new ModelAndView("/admin/product/manageProduct");
		dataList = service.findDaList(0L, new productEntity());
//		mav.addObject("dataList",dataList);;
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		return mav;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/addProduct" }, method = RequestMethod.GET)
	public ModelAndView addProductView() {
		service = new productService();
		mav = new ModelAndView("/admin/product/addProduct");
		dataList = service.findDaList(0L, new productEntity());
		addData();
		return mav;
	}

	@RequestMapping(value = { "/admin/addProduct" }, method = RequestMethod.POST)
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

	@ModelAttribute("productSearch")
	productEntity productSearch() {
		return new productEntity();
	}

}
