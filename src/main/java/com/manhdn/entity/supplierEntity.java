package com.manhdn.entity;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/*
 * supplierEntity
 * thuong hieu
 */
public class supplierEntity extends CommonEntity {
	private String supplierId;
	private String supplierName;
	private String address;
	private String phoneNumber;
	private String email;
	private String website;
	private Long status;
	private String location;
	private String logo;
	// file logo
	private CommonsMultipartFile[] fileLogo;
	public CommonsMultipartFile[] getFileLogo() {
		return fileLogo;
	}
	public void setFileLogo(CommonsMultipartFile[] fileLogo) {
		this.fileLogo = fileLogo;
	}
	public String getStatusString() {
		return this.status==1?"Hoạt động":"Không hoạt động";
	}
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private String description;

	/**
	 * getter setter
	 * 
	 * @return
	 */
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
