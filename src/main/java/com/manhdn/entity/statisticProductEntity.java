package com.manhdn.entity;

import java.text.DecimalFormat;
import java.util.List;

import com.manhdn.FunctionCommon;

public class statisticProductEntity implements Comparable {
	private String fromDate;
	private String toDate;
	private Long quantity;
	private productEntity product;
	private productEntity productSearch;
	private List<orderEntity> listOrder;
	private Long total;
	
	public String getTotalString() {
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		return myFormatter.format(total);
	}
	
	public productEntity getProductSearch() {
		return productSearch;
	}

	public void setProductSearch(productEntity productSearch) {
		this.productSearch = productSearch;
	}


	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public productEntity getProduct() {
		return product;
	}
	public void setProduct(productEntity product) {
		this.product = product;
	}
	public List<orderEntity> getListOrder() {
		return listOrder;
	}
	public void setListOrder(List<orderEntity> listOrder) {
		this.listOrder = listOrder;
	}
	public Long getTotal() {
		if (this.total!=null && this.total > 0L) {
			return total;
		}
		if (FunctionCommon.isEmpty(getListOrder())) {
			return 0L;
		}
		Long t = 0L;
		for (orderEntity o : getListOrder()) {
			t += o.getTotal();
		}
		return t;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

	

	@Override
	public int compareTo(Object o) {
		int compare=((statisticProductEntity)o).getTotal().intValue();
		
        /* For Ascending order*/
        return compare - this.getTotal().intValue();
	}
	
	
}
