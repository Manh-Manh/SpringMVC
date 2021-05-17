package com.manhdn.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.manhdn.AppConstants;

public class discountEntity extends CommonEntity {

	private String discountId;
	private Double discount;
	private String startDate;
	private String endDate;
	private Long status;
	private String description;
	private String productId;
	public String getProductId() {
		return productId;
	}
	public String getStringActive() {
		if(AppConstants.STATUS_BLOCK == status) {
			 return "Không áp dụng";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
        try {
			Date dateN = sdf.parse(dateNow);
			Date dateEnd = sdf.parse(endDate);
			if(dateN.compareTo(dateEnd) <= 0) {
				return "Đang áp dụng";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return "Không áp dụng";
        
	}
	public String getStatusString() {
		return this.status==1?"Đang hoạt động":"Không hoạt động";
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

}
