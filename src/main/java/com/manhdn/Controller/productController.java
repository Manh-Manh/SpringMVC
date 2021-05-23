package com.manhdn.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.manhdn.dao.discountDAO;
import com.manhdn.dao.productDAO;
import com.manhdn.entity.ajaxEntity;
import com.manhdn.entity.commentEntity;
import com.manhdn.entity.discountEntity;
import com.manhdn.entity.faceEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.service.commentService;
import com.manhdn.service.faceService;
import com.manhdn.service.productService;
import com.manhdn.service.statisticProductService;

@Controller
@Component
public class productController extends CommonController<productEntity> {
	private static final Logger logger = Logger.getLogger(productController.class);
	@Autowired
	productService service;
	List<productEntity> listSale = new ArrayList<productEntity>();
	List<productEntity> listNew = new ArrayList<productEntity>();
	List<productEntity> listSuggest = new ArrayList<productEntity>();
	@RequestMapping(value = { "/app-view/home-page", "/app-view/homePage" }, method = RequestMethod.GET)
	public @ResponseBody ModelAndView homePage() {
		service = new productService();
		message ="";
		mav = new ModelAndView("/user/homePage");
		message="";
		refreshMap();
		count = service.countDataList(0L, mapSearch);
		dataList = service.findDaList(0L, mapSearch, page, pageSize);
//		getDataFromLink();
//		List<productEntity> dataList2= new ArrayList<productEntity>();
//		dataList2.addAll(dataList);
//		mav.addObject("dataList2",dataList2);
		addAttribute();
		logger.info(mav);
		return mav;
	}
	
	@RequestMapping(value = { "/app-view/shop" }, method = RequestMethod.GET)
	public @ResponseBody ModelAndView shop() {
		message="";
		service = new productService();
		mav = new ModelAndView("/user/home");
		refreshMap();
		count = service.countDataList(0L, mapSearch);
		dataList = service.findDaList(0L, mapSearch, page, pageSize);
		this.page =1;
		addAttribute();
		logger.info(mav);
		return mav;
	}
	
	@RequestMapping(value = { "/app-view" }, method = RequestMethod.GET)
	public @ResponseBody ModelAndView home(@RequestParam(value = "page", required = false) Integer page) {
		service = new productService();
		message ="";
		mav = new ModelAndView("/user/home");
		if (isReload) {
			count = service.countDataList(0L, mapSearch);
			dataList = service.findDaList(0L, mapSearch, page, pageSize);
		}
		if (page != null) {
			this.page = page;
		}
		
		statisticProductService sss = new statisticProductService();
		sss.findDaList(0L, null);
		
		addAttribute();
		logger.info(mav);
		return mav;
	}

	/**
	 * @author manhnd Xem chi tiet
	 * @param id
	 */
	@RequestMapping(value = { "/app-view/viewDetail" }, method = RequestMethod.GET)
	public ModelAndView viewDetail(@RequestParam("id") String id, HttpServletResponse res) {
		mav = new ModelAndView("/user/viewDetail");
		res.setCharacterEncoding("UTF-8");
		message ="";
		service = new productService();
		dataSelected = service.getProductDetail(id);
		List<commentEntity> listCmt = new ArrayList<commentEntity>();
		commentService cmtS = new commentService();
		listCmt = cmtS.getAllCommetByProductId(id);
		map.addAttribute("listComment", listCmt);
		addAttribute();
		logger.info(mav);
		return mav;
	}

	/**
	 * 
	 * @param productSearch
	 * @param session
	 * @return
	 */
	@RequestMapping(value = { "/app-view/quickSearch" }, method = RequestMethod.POST)
	public String quickSearch(@RequestBody String data) {
		service = new productService();
		this.isReload = false;
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
		type = "quickSearch";
//		this.dataSearch = productSearch;		
//		mav = new ModelAndView("/user/home");
		this.page = Integer.valueOf(mapSearch.get(AppConstants.MAP_SEARCH_PAGE).get(0));
		if (FunctionCommon.isEmpty(mapSearch.get(AppConstants.MAP_SEARCH_STRING))){
			this.type = "";
		}
		mapSearch.remove("page");
		count = service.countDataList(0L, mapSearch);
		dataList = service.findDaList(0L, mapSearch, page, pageSize);
		this.isReload = false;
		addAttribute();
		logger.info(mav);
		return "quickSearch";
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
		this.page = Integer.valueOf(mapSearch.get(AppConstants.MAP_SEARCH_PAGE).get(0));
		if (FunctionCommon.isEmpty(mapSearch.get(AppConstants.MAP_SEARCH_SUPPLIER_ID))
				&& FunctionCommon.isEmpty(mapSearch.get(AppConstants.MAP_SEARCH_MACHINE_ID))
				&& FunctionCommon.isEmpty(mapSearch.get(AppConstants.MAP_SEARCH_STRAP_ID))
				&& FunctionCommon.isEmpty(mapSearch.get(AppConstants.MAP_SEARCH_STRING))) {
			this.type = "";
		}
		mapSearch.remove("page");
		count = service.countDataList(0L, mapSearch);
		dataList = service.findDaList(0L, mapSearch, page, pageSize);
		this.isReload = false;
		
		addAttribute();
		logger.info(mav);
		return "advSearch";
	}

	/**
	 * QLSP
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/manageProduct" }, method = RequestMethod.GET)
	public ModelAndView manageProduct(HttpSession session) {
		service = new productService();
		mav = new ModelAndView("/admin/product/manageProduct");
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		dataList = service.findAll();
//		mav.addObject("dataList",dataList);;
//		service.insertOrUpdate(0L, dataSearch);
		addAttribute();
		logger.info(mav);
		return mav;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/addProduct" }, method = RequestMethod.GET)
	public ModelAndView addProductView(HttpSession session) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new productService();
		mav = new ModelAndView("/admin/product/addProduct");
		discountDAO disD = new discountDAO();
		List<discountEntity> lstDiscount = disD.findDataList(0L, null);
		map.addAttribute("lstDiscount", lstDiscount);
		map.addAttribute("isInsert", 1);
		dataList = service.findDaList(0L, new productEntity());
		addAttribute();
		logger.info(mav);
		return mav;
	}

//	@RequestMapping(value = { "/admin/addProduct" }, method = RequestMethod.POST)
//	public ModelAndView addProduct(@ModelAttribute("dataInsert") productEntity dataInsert, HttpSession session) {
//		service = new productService();
//		mav = new ModelAndView("/admin/product/addProduct");
//		if(!isAdmin(session)) {
//			mav = new ModelAndView("redirect:/app-view");
//			logger.error("Khong co quyen");
//			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
//			return mav;
//		}
//		service.insertOrUpdate(0L, dataSearch);
//		addAttribute();
//		logger.info(mav);
//		return mav;
//	}

	@RequestMapping(value = { "/admin/addNewProduct" }, method = RequestMethod.POST)
	public ModelAndView addNewProduct(@ModelAttribute("dataInsert") productEntity dataInsert, HttpSession session,
			HttpServletRequest request) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new productService();
		mav = new ModelAndView("/admin/product/addProduct");
		discountDAO disD = new discountDAO();
		List<discountEntity> lstDiscount = disD.findDataList(0L, null);
		
		// upload Anh
		if(dataInsert.getFileImage() != null && dataInsert.getFileImage().length >0 ) {
			if(!FunctionCommon.isEmpty(dataInsert.getFileImage()[0].getOriginalFilename())) {
				String upload = this.doUpload(request, dataInsert);
				dataInsert.setImage(dataInsert.getFileImage()[0].getOriginalFilename());
			}
		}
		if(service.insertOrUpdate(0L, dataInsert)) {
			message = "Cập nhật thành công!";
		}else {
			message= AppConstants.MESSAGE_ERROR;
		}
		dataSelected = service.getProductDetail(dataInsert.getProductId());
		mav = new ModelAndView("/admin/product/addProduct");
		session.setAttribute(AppConstants.SESSION_MESSAGE, message);
		dataSelected = service.getProductDetail(dataInsert.getProductId());
		
		map.addAttribute("lstDiscount", lstDiscount);
//		dataList = service.findDaList(0L, new productEntity());
		addAttribute();
		logger.info(mav);
		return this.editProductView(session, dataInsert.getProductId());
//		return mav;
	}

	
	@RequestMapping(value = { "/admin/editProduct" }, method = RequestMethod.GET)
	public ModelAndView editProductView(HttpSession session, @RequestParam("productId") String productId) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		if(productId == null) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Loi id null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, "Liên hệ quản trị hệ thống để được hỗ trợ");
			return mav;
		}
		service = new productService();
		dataSelected = service.getProductDetail(productId);
		// list Face
		faceService faceS = new faceService();
		List<faceEntity> lstFace = new ArrayList<faceEntity>();
		lstFace = faceS.findDaList(null, null);
		map.addAttribute("lstFace", lstFace);
		mav = new ModelAndView("/admin/product/addProduct");
		discountDAO disD = new discountDAO();
		List<discountEntity> lstDiscount = disD.findDataList(0L, null);
		List<discountEntity> lstDiscount2 = new ArrayList<discountEntity>();
		
		// remove discount 
		for(discountEntity d : lstDiscount) {
			lstDiscount2.add(d);
		}
		
		if(!FunctionCommon.isEmpty(dataSelected.getLstDiscount())) {
			for(int  i =  0; i< dataSelected.getLstDiscount().size();i++) {
				String id = dataSelected.getLstDiscount().get(i).getDiscountId();
				for(int j =0;j< lstDiscount2.size();j++) {
					if(id.equals(lstDiscount2.get(j).getDiscountId())) {
						lstDiscount2.remove(j);
						j--;
					}
				}
			}
		}
		map.addAttribute("isInsert", 0);
		map.addAttribute("lstDiscount", lstDiscount2);
		addAttribute();
		logger.info(mav);
		return mav;
	}
	private void addAttribute() {
		listSale = service.findListSale();
		listSuggest = service.findListSuggest(this.dataSelected);
		listNew = service.finListNew();
		map.addAttribute("listSale", listSale);
		map.addAttribute("listNew", listNew);
		map.addAttribute("listSuggest", listSuggest);
		super.addData();
		
	}
	
	@RequestMapping(value = { "/admin/doSearchProduct" }, method = RequestMethod.POST)
	public ModelAndView doSearchProduct(@ModelAttribute("productSearch") productEntity productSearch, HttpSession session,
			HttpServletRequest request) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new productService();
		mav = new ModelAndView("/admin/product/manageProduct");
		discountDAO disD = new discountDAO();
		List<discountEntity> lstDiscount = disD.findDataList(0L, null);
		
		map.addAttribute("lstDiscount", lstDiscount);
		dataList = service.findDaList(0L, productSearch);
		addAttribute();
		logger.info(mav);
//		return this.editProductView(session, dataInsert.getProductId());
		return mav;
	}
	private static void getDataFromLink()
	{
	    final String uri = "https://thongtindoanhnghiep.co/api/city";

	    RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

//	    String result = restTemplate.getForObject(uri, String.class);
//
//	    System.out.println(result);
        Object response = restTemplate.exchange(uri, HttpMethod.GET,entity,Object.class);
        System.out.println(response);
        Gson gson = new GsonBuilder().create();
        ResponseEntity<Class<? extends Object>> r = (ResponseEntity<Class<? extends Object>>) response;
        Class<? extends Object> map = r.getBody();
        String s =response.toString();
        
        String json = gson.toJson(r.getBody(), r.getBody().getClass());
        
        
	    	Class<? extends Object> res = response.getClass();
	    	
	}
	private String doUpload(HttpServletRequest request, productEntity prUpload) {

		// Thư mục gốc upload file.
		String uploadRootPath = request.getServletContext().getRealPath("upload");
//		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath);
		//
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		CommonsMultipartFile[] fileDatas = prUpload.getFileImage();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		for (CommonsMultipartFile fileData : fileDatas) {

			// Tên file gốc tại Client.
			String name = fileData.getOriginalFilename();
//			System.out.println("Client File Name = " + name);

			if (name != null && name.length() > 0) {
				try {
					// Tạo file tại Server.
					String urlDir = "\\..\\WEB-INF\\views\\assets\\images\\products";
					File serverFile = new File(uploadRootDir.getAbsolutePath() + urlDir + File.separator + name);

					// Luồng ghi dữ liệu vào file trên Server.
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					// Tao file vao project
					urlDir = "D:\\java\\web\\SpringMVC\\src\\main\\webapp\\WEB-INF\\views\\assets\\images\\products";
					serverFile = new File(urlDir + File.separator + name);

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
	
	@ModelAttribute("dataInsert")
	productEntity dataIn() {
		return new productEntity();
	}

	@ModelAttribute("productSearch")
	productEntity productSearch() {
		return new productEntity();
	}
	@ModelAttribute("comment")
	public commentEntity comment() {
		return new commentEntity();
	}
}
