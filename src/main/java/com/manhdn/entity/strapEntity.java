package com.manhdn.entity;

public class strapEntity extends CommonEntity {
	private String strapId;
	private String strapName;
	private String materialStrap;
	private String description;
	private Long status;
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

	public String getStrapName() {
		return strapName;
	}

	public void setStrapName(String strapName) {
		this.strapName = strapName;
	}

	public String getMaterialStrap() {
		return materialStrap;
	}

	public void setMaterialStrap(String materialStrap) {
		this.materialStrap = materialStrap;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
