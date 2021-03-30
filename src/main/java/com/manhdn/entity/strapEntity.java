package com.manhdn.entity;

public class strapEntity extends CommonEntity<strapEntity> {
	private String strapId;
	private String strapName;
	private String materialStrap;
	private String description;

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
