package com.manhdn.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class CommonController<T> {
	protected List<T> dataList;
	protected T dataSearch;
	protected T dataSelected;
	protected ModelAndView mav;
	protected ModelMap map = new ModelMap();

	public CommonController() {
		map.addAttribute("dataList", dataList);
		map.addAttribute("dataSearch", dataSearch);
		map.addAttribute("dataSelected", dataSelected);
	}
	
	public void addData() {
		map.addAttribute("dataList", dataList);
		map.addAttribute("dataSearch", dataSearch);
		map.addAttribute("dataSelected", dataSelected);
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

}
