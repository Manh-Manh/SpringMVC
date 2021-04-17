package com.manhdn.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.AppConstants;
import com.manhdn.entity.ajaxEntity;
import com.manhdn.entity.productEntity;

public class CommonController<T> {
	protected List<T> dataList;
	protected Integer page = 1;
	protected String type ="";
	protected Integer count=0;
	protected Integer pageSize = AppConstants.PAGE_SIZE;
	protected T dataSearch;
	protected T dataSelected;
	protected boolean isCount= true;
	protected boolean isReload = true;
	protected Map<String, List<String>> mapSearch = new HashMap<String, List<String>>();
	protected List<ajaxEntity> lstSearch =new ArrayList<ajaxEntity>();
	protected ModelAndView mav;
	protected ModelMap map = new ModelMap();
	protected String message="";

	public CommonController() {
		
		List<String> l =new ArrayList<String>();
		l.add("5000000");l.add("15000000");
		mapSearch.put(AppConstants.MAP_SEARCH_AMOUNT,l);
		map.addAttribute("dataList", dataList);
		map.addAttribute("dataSearch", dataSearch);
		map.addAttribute("dataSelected", dataSelected);
		map.addAttribute("mapSearch", mapSearch);
		map.addAttribute("lstSearch", lstSearch);
		map.addAttribute("page", page);
		map.addAttribute("count", count);
		map.addAttribute("type", type);
		map.addAttribute("message", message);
		
	}
	public void refreshMap() {
		mapSearch = new HashMap<String, List<String>>();
		List<String> l =new ArrayList<String>();
		l.add("5000000");l.add("15000000");
		mapSearch.put(AppConstants.MAP_SEARCH_AMOUNT,l);
	}
	public void addData() {
		map.addAttribute("dataList", dataList);
		map.addAttribute("dataSearch", dataSearch);
		map.addAttribute("dataSelected", dataSelected);
		map.addAttribute("mapSearch", mapSearch);
		map.addAttribute("lstSearch", lstSearch);
		map.addAttribute("page", page);
		map.addAttribute("count", count);
		map.addAttribute("type", type);
		map.addAttribute("message", message);
		mav.addAllObjects(map);
	}
	public T getDataSelected() {
		return dataSelected;
	}

	public void setDataSelected(T dataSelected) {
		this.dataSelected = dataSelected;
	}

	public ModelAndView getMav() {
		return mav;
	}

	public void setMav(ModelAndView mav) {
		this.mav = mav;
	}

	
	public ModelMap getMap() {
		return map;
	}

	public void setMap(ModelMap map) {
		this.map = map;
	}

	public ModelAndView getModelAndView() {
		return mav;
	}

	public void setModelAndView(ModelAndView modelAndView) {
		this.mav = modelAndView;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public T getDataSearch() {
		return dataSearch;
	}

	public void setDataSearch(T dataSearch) {
		this.dataSearch = dataSearch;
	}

	public boolean isReload() {
		return isReload;
	}

	public void setReload(boolean isReload) {
		this.isReload = isReload;
	}

	public Map<String, List<String>> getMapSearch() {
		return mapSearch;
	}

	public void setMapSearch(Map<String, List<String>> mapSearch) {
		this.mapSearch = mapSearch;
	}

	public List<ajaxEntity> getLstSearch() {
		return lstSearch;
	}

	public void setLstSearch(List<ajaxEntity> lstSearch) {
		this.lstSearch = lstSearch;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public boolean isCount() {
		return isCount;
	}

	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@ModelAttribute("productSearch")
	productEntity productSearch() {
		return new productEntity();
	}	
	@ModelAttribute("prodSelected")
	public productEntity prodSelected() {
		return new productEntity();
	}
}
