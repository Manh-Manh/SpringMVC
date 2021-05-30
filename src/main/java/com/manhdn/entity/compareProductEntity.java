package com.manhdn.entity;

import java.text.DecimalFormat;

import com.manhdn.FunctionCommon;

public class compareProductEntity {
	private productEntity product;
	private String url;
	private String img_link;
	private String domain;
	private String productName;
	private Long unitPrice;
	private Long discountPrice;
	
	
	public String getStringUnitPrice() {
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		return myFormatter.format(unitPrice);
	}
	
	public String getStringDiscountPrice() {
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		return myFormatter.format(this.getDiscountPrice());
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Long getDiscountPrice() {
		if( this.discountPrice >= this.unitPrice) {
			this.unitPrice = this.discountPrice;
			return 0L;
		}
		return discountPrice;
	}
	public void setDiscountPrice(Long discountPrice) {
		this.discountPrice = discountPrice;
	}
	public productEntity getProduct() {
		if(this.product != null) {
			return this.product;
		}
		productEntity p =new productEntity();
		if(!FunctionCommon.isEmpty(this.productName)) {
			p.setProductName(this.productName);
		}
		if(this.unitPrice != null) {
			p.setUnitPrice(this.unitPrice);
		}
		if(this.discountPrice != null) {
			p.setDisCountPrice(this.discountPrice);
		}
		return p;
	}
	public void setProduct(productEntity product) {
		this.product = product;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg_link() {
		return img_link;
	}
	public void setImg_link(String img_link) {
		this.img_link = img_link;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDomainString() {
		return "https://"+domain;
	}
}
