package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.productDAO;
import com.manhdn.dao.supplierDAO;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.supplierEntity;

@Service
public class supplierService {
	@Autowired
	supplierDAO dao;
	
	/**
	 * 
	 * @param userId
	 * @param dataSearch
	 * @return
	 */
	public List<supplierEntity> findDaList(Long userId, productEntity dataSearch) {
		dao =new supplierDAO();
		return dao.findDataList(userId, dataSearch);
	}
}
