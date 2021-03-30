package com.manhdn.entity;

/*
 * supplierEntity
 * thuong hieu
 */
public class supplierEntity extends CommonEntity<supplierEntity> {
	private String supplierId;
	private String supplierName;
	private String address;
	private String phoneNunber;
	private String email;
	private String website;
	private String status;
	private String location;

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

	public String getPhoneNunber() {
		return phoneNunber;
	}

	public void setPhoneNunber(String phoneNunber) {
		this.phoneNunber = phoneNunber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
