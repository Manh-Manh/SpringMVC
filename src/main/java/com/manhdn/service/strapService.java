package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
