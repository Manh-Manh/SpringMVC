package com.manhdn.Controller;

import java.util.List;

public class CommonController<T> {
	private List<T> dataList;
	private T dataSearch;
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
