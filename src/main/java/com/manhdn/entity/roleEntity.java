package com.manhdn.entity;

public class roleEntity extends CommonEntity {
	private String roleId;
	private Long roleLevel;
	private String description;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Long getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Long roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
