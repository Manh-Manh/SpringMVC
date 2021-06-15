package com.manhdn.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.manhdn.FunctionCommon;

public class orderEntity extends CommonEntity {
	private String orderId;
	private Long userId;
	private Long status;
	private List<productEntity> listProduct;
	private String orderDate;
	private String toDate;	
	private String fromDate;
	// nguoi mua
	private String start_date_discount;
	private String end_date_discount; 
	private userEntity user;
	public userEntity getUser() {
		return user;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getStart_date_discount() {
		return start_date_discount;
	}

	public void setStart_date_discount(String start_date_discount) {
		this.start_date_discount = start_date_discount;
	}

	public String getEnd_date_discount() {
		return end_date_discount;
	}

	public void setEnd_date_discount(String end_date_discount) {
		this.end_date_discount = end_date_discount;
	}

	public void setUser(userEntity user) {
		this.user = user;
	}

	public String getOrderId() {
		return orderId;
	}

	public orderEntity() {
		listProduct = new ArrayList<productEntity>();
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	public List<productEntity> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<productEntity> listProduct) {
		this.listProduct = listProduct;
	}
	
	public Long getSubTotal() {
		if (FunctionCommon.isEmpty(this.listProduct)) {
			return 0L;
		} else {
			Long t = 0L;
			for (productEntity p : this.listProduct) {
				t += p.getSubCartTotal();
			}
			return t;
		}
	}
	
	public Long getTotal() {
		if (FunctionCommon.isEmpty(this.listProduct)) {
			return 0L;
		} else {
			Long t = 0L;
			for (productEntity p : this.listProduct) {
				t += p.getCartTotal();
			}
			return t;
		}
	}
	// String total
	public String  getTotalString() {
		Long t = this.getTotal();
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		return myFormatter.format(t);
	}
	// String status
	public String getStatusString() {
		if(this.status == 1L) {
			return "Chưa đặt hàng.";
		}else if (this.status == 2L) {
			return "Đang xử lý.";
		}else if (this.status == 3L) {
			return "Đã xử lý.";
		}else if(this.status == 4L) {
			return "Đã hủy.";
		}else {
			return "";
		}
	}
	public Long getTotalDiscount() {
		Long d=0L;
//		if(!FunctionCommon.isEmpty(this.listProduct)) {
//			for(productEntity p :listProduct) {
//				d+=(p.getSubCartTotal() - p.getDisCountPrice());
//			}
//		}
		d = this.getSubTotal() - this.getTotal();
		return d;
	}
	public String  getTotalDiscountString() {
		Long t = this.getTotalDiscount();
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		return myFormatter.format(t);
	}
	
	public String  getSubTotalString() {
		Long t = this.getSubTotal();
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		return myFormatter.format(t);
	}
}
