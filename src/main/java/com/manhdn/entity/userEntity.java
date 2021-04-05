package com.manhdn.entity;

import com.manhdn.AppConstants;

public class userEntity extends CommonEntity {
	private String userId;
	private String userName;
	private String password;
	private String email;
	private String address;
	private String phoneNumber;
	private Long status;
	private String avatar;
	private String birthDate;
	private String del_flag;
	private roleEntity role;
	private userEntity son;
	public userEntity getSon() {
		return son;
	}

	public void setSon(userEntity son) {
		this.son = son;
	}

	public roleEntity getRole() {
		return role;
	}

	public void setRole(roleEntity role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getStatusString() {
		if(status == 1 || status == null) {
			return AppConstants.ACTIVE;
		}
		else{
			return AppConstants.BLOCK;
		}
	}
}
