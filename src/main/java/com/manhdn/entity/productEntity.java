package com.manhdn.entity;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.manhdn.FunctionCommon;

public class productEntity extends CommonEntity {

	private String productName;
	private Long quantity;
	private Long cartQuantity = 1L;
	private String productId;
	private String supplierId;
	private Long unitPrice;
	private String gender;
	private Double discount;
	private Long status;
	private String strapId;
	private String faceId;
	private String machineId;
	private String location;
	private String material;
	private String otherFunc;
	private String image;
	private String description;
	private String strLstDiscount;// danh sach discountId - string
	private Long cartTotal;
	private String cartTotalString;
	private List<discountEntity> lstDiscount;
	// Supplier
	private supplierEntity supplier;
	// Strap
	private strapEntity strap;
	private faceEntity face;
	private machineEntity machine;
	private Long disCountPrice = 0L;
	// file image
	private CommonsMultipartFile[] fileImage;
	public CommonsMultipartFile[] getFileImage() {
		return fileImage;
	}

	public void setFileImage(CommonsMultipartFile[] fileImage) {
		this.fileImage = fileImage;
	}

	public String getStrLstDiscount() {
		return strLstDiscount;
	}

	public void setStrLstDiscount(String strLstDiscount) {
		this.strLstDiscount = strLstDiscount;
	}

	public List<discountEntity> getLstDiscount() {
		return lstDiscount;
	}

	public void setLstDiscount(List<discountEntity> lstDiscount) {
		this.lstDiscount = lstDiscount;
	}

	public Long getDisCountPrice() {
		if (unitPrice != null && unitPrice > 0 && getDiscount() != null && getDiscount() > 0) {
			disCountPrice = (long) ((1 - 0.01 * getDiscount()) * unitPrice);
		}
		return disCountPrice;
	}

	public void setDisCountPrice(Long disCountPrice) {
		this.disCountPrice = disCountPrice;
	}

	public strapEntity getStrap() {
		return strap;
	}

	public void setStrap(strapEntity strap) {
		this.strap = strap;
	}

	public faceEntity getFace() {
		return face;
	}

	public void setFace(faceEntity face) {
		this.face = face;
	}

	public machineEntity getMachine() {
		return machine;
	}

	public void setMachine(machineEntity machine) {
		this.machine = machine;
	}

	public supplierEntity getSupplier() {
		return supplier;
	}

	public void setSupplier(supplierEntity supplier) {
		this.supplier = supplier;
	}

	// public String getProductName() {
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

	public String getStringDiscountPrice() {
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		return myFormatter.format(this.getDisCountPrice());
	}

	public String getStringUnitPrice() {
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		return myFormatter.format(unitPrice);
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


	public Double getDiscount() {
		if (FunctionCommon.isEmpty(lstDiscount)) {
			return 0D;
		}
//		if (discount != null || discount > 0D) {
//			return discount;
//		}
		discount = 0D;
		for (discountEntity d : lstDiscount) {
			discount += d.getDiscount();
		}
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}


	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
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

	public Long getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(Long cart_quantity) {
		this.cartQuantity = cart_quantity;
	}
	public Long getSubCartTotal() {
		return cartQuantity *  getUnitPrice();
	}
	public Long getCartTotal() {
		return cartQuantity * (getDisCountPrice() > 0 ? getDisCountPrice() : getUnitPrice());
	}

	public void setCartTotal(Long cartTotal) {
		this.cartTotal = cartTotal;
	}

	public String getCartTotalString() {
		DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
		Long total = 0L;
		total = cartQuantity * (getDisCountPrice() > 0 ? getDisCountPrice() : getUnitPrice());
		return myFormatter.format(total);
	}

	public void setCartTotalString(String cartTotalString) {
		this.cartTotalString = cartTotalString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatusString() {
		return this.status==1?"Hoạt động":"Không hoạt động";
	}
}
