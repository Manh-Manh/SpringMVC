package com.manhdn.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;

public class userEntity extends CommonEntity {
	private Long userId;
	private String userName;
	private String fullName;
	private String password;
	private String newPassword;
	private String email;
	private String address;
	private String phoneNumber;
	private Long status;
	private String avatar;
	private String path;
	private String birthDate;
	private List<roleEntity> lstRoles;
	private CommonsMultipartFile[] fileAvatar;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<roleEntity> getLstRoles() {
		return lstRoles;
	}

	public void setLstRoles(List<roleEntity> lstRoles) {
		this.lstRoles = lstRoles;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public CommonsMultipartFile[] getFileAvatar() {
		return fileAvatar;
	}

	public void setFileAvatar(CommonsMultipartFile[] fileAvatar) {
		this.fileAvatar = fileAvatar;
	}

	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public String getStatusString() {
		if(status == 1 || status == null) {
			return AppConstants.ACTIVE;
		}
		else{
			return AppConstants.BLOCK;
		}
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public List<String> getListRoleName() {
		if(FunctionCommon.isEmpty(getLstRoles())) {
			return null;
		}
		List<String> lst = new ArrayList<String>();
		for(roleEntity r: getLstRoles()) {
			lst.add(r.getRoleName());
		}
		return lst;
	}
}
