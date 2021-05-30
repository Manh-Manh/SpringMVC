package com.manhdn.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.manhdn.AppConstants;
import com.manhdn.crawl.Crawler;
import com.manhdn.entity.ajaxEntity;
import com.manhdn.entity.commentEntity;
import com.manhdn.entity.compareProductEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.service.commentService;
import com.manhdn.service.productService;
@Controller
public class compareController extends CommonController<compareProductEntity>{
//	@Autowired
//	compareService service;
	private Logger logger = Logger.getLogger(compareController.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/app-view/compare" }, method = RequestMethod.GET)
	public ModelAndView viewDetail(@RequestParam("id") String id, HttpServletResponse res) {
		mav = new ModelAndView("/user/compare");
		res.setCharacterEncoding("UTF-8");
		message = "";
		productService prS = new productService();
		dataSelected = new compareProductEntity();
		productEntity  p = prS.getProductDetail(id);
		dataSelected.setProduct(p);
		
		//Lay String
		dataList = new ArrayList<compareProductEntity>();
		Crawler crawl = new Crawler();
		String keySearch = p.getProductName();
		String strResult = crawl.getDataFromGoogle(keySearch);
		Gson gson = new GsonBuilder().create();
		JsonParser parser = new JsonParser();
		JsonArray arr = parser.parse(strResult).getAsJsonArray();
		for (int i = 0; i < arr.size(); i++) {
			compareProductEntity o = (compareProductEntity) gson.fromJson(arr.get(i), compareProductEntity.class);
			dataList.add(o);
		}
		
		//
		productService proS = new productService();
		List<productEntity> listSuggest = proS.findListSuggest(this.dataSelected.getProduct());
		map.addAttribute("listSuggest", listSuggest);
		addData();
		logger.info(mav);
		return mav;
	}
	
}
