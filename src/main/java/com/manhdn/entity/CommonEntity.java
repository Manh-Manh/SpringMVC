package com.manhdn.entity;

import java.util.List;

/*
 * 
 */
public class CommonEntity<T> {
	private Long id;
	List<T> dataList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	protected String created_date;
	protected String updated_date;
	protected String deleted_date;
	protected Long created_by;
	protected Long updated_by;
	protected Long deleted_by;

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public String getDeleted_date() {
		return deleted_date;
	}

	public void setDeleted_date(String deleted_date) {
		this.deleted_date = deleted_date;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public Long getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(Long deleted_by) {
		this.deleted_by = deleted_by;
	}
}
