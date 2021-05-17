package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.machineDAO;
import com.manhdn.dao.productDAO;
import com.manhdn.dao.strapDAO;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.strapEntity;

@Service
public class strapService {
	@Autowired
	strapDAO dao;
	
	/**
	 * 
	 * @param userId
	 * @param dataSearch
	 * @return
	 */
	public List<strapEntity> findDaList(Long userId, productEntity dataSearch) {
		dao =new strapDAO();
		return dao.findDataList(userId, dataSearch);
	}

	public List<strapEntity> getAllStrap() {
		dao =new strapDAO();
		return dao.getAllStrap();
	}

	public strapEntity getDetail(String strapId) {
		dao =new strapDAO();
		return dao.getDetail(strapId);
	}

	public boolean insertOrUpdate(Long userId, strapEntity dataInsert) {
		dao =new strapDAO();
		return dao.insertOrUpdate(userId, dataInsert);
	}

	public boolean delete(Long userId, String strapId) {
		dao =new strapDAO();
		return dao.delete(userId, strapId);
	}
}
