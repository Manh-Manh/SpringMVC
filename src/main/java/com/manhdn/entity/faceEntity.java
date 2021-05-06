package com.manhdn.entity;

public class faceEntity extends CommonEntity {
	private String faceId;
	private String faceName;
	private String thickness;
	private String faceSize;
	private String glass;
	private String waterProof;
	private String description;
	private String status;

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
	public String getThickness() {
		return thickness;
	}
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}
	public String getFaceSize() {
		return faceSize;
	}
	public void setFaceSize(String faceSize) {
		this.faceSize = faceSize;
	}
	public String getGlass() {
		return glass;
	}
	public void setGlass(String glass) {
		this.glass = glass;
	}
	public String getWaterProof() {
		return waterProof;
	}
	public void setWaterProof(String waterProof) {
		this.waterProof = waterProof;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}