package com.manhdn.entity;

public class productEntity extends CommonEntity<productEntity> {
	
	private String productName;
	private Long quantity;
	private String productId;
	private String supplierId;
	private Long unitPrice;
	private String gender;
	private String startDate_discount;
	private Double discount;
	private String endDate_discount;
	private String status;
	private String strapId;
	private String faceId;
	private String machineId;
	private String location;
	private String material;
	private String otherFunc;
	private String image;
	private String del_flag;

//	public String getProductName() {
//		return productName;
//	}
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStartDate_discount() {
		return startDate_discount;
	}

	public void setStartDate_discount(String startDate_discount) {
		this.startDate_discount = startDate_discount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getEndDate_discount() {
		return endDate_discount;
	}

	public void setEndDate_discount(String endDate_discount) {
		this.endDate_discount = endDate_discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStrapId() {
		return strapId;
	}

	public void setStrapId(String strapId) {
		this.strapId = strapId;
	}

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getOtherFunc() {
		return otherFunc;
	}

	public void setOtherFunc(String otherFunc) {
		this.otherFunc = otherFunc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
}
