package com.manhdn.entity;

import java.util.List;

public class commentEntity extends CommonEntity {

	private String commentId;
	private Long userId;
	private String productId;
	private Long status;
	private String content;
	private userEntity user;
	private String parentId;
	private List<commentEntity> listCmtReply;
	public List<commentEntity> getListCmtReply() {
		return listCmtReply;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setListCmtReply(List<commentEntity> listCmtReply) {
		this.listCmtReply = listCmtReply;
	}

	public userEntity getUser() {
		return user;
	}

	public void setUser(userEntity user) {
		this.user = user;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
