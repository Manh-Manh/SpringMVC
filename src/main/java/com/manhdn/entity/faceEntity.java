package com.manhdn.entity;

public class faceEntity extends CommonEntity {
	private String faceId;
	private String faceName;
	private Double thickness;
	private Double faceSize;
	private String glass;
	private Double waterProof;
	private String description;
	private Long status;
	
	public String getStatusString() {
		return this.status==1?"Hoạt động":"Không hoạt động";
	}
	public String getDetailString() {
		return faceName + " (Dày: "+faceSize+" mm; Rộng: "+thickness+" mm)";
	}
	public String getFaceId() {
		return faceId;
	}
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	public String getFaceName() {
		return faceName;
	}
	public void setFaceName(String faceName) {
		this.faceName = faceName;
	}
	public Double getThickness() {
		return thickness;
	}
	public void setThickness(Double thickness) {
		this.thickness = thickness;
	}
	public Double getFaceSize() {
		return faceSize;
	}
	public void setFaceSize(Double faceSize) {
		this.faceSize = faceSize;
	}
	public String getGlass() {
		return glass;
	}
	public void setGlass(String glass) {
		this.glass = glass;
	}
	public Double getWaterProof() {
		return waterProof;
	}
	public void setWaterProof(Double waterProof) {
		this.waterProof = waterProof;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
}