package com.manhdn.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.manhdn.FunctionCommon;

public class orderEntity extends CommonEntity {
	private String orderId;
	private Long userId;
	private Long status;
	private String del_flag;
	private List<productEntity> listProduct;

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

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public List<productEntity> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<productEntity> listProduct) {
		this.listProduct = listProduct;
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
}
