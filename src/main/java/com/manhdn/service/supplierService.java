package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.supplierDAO;
import com.manhdn.dao.productDAO;
import com.manhdn.dao.supplierDAO;
import com.manhdn.entity.supplierEntity;
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
	public List<supplierEntity> findDaList(Long userId, supplierEntity dataSearch) {
		dao =new supplierDAO();
		return dao.findDataList(userId, dataSearch);
	}

	
	public List<supplierEntity> getAllSupplier() {
		dao =new supplierDAO();
		return dao.getAllSupplier();
	}

	public supplierEntity getDetail(String supplierId) {
		dao =new supplierDAO();
		return dao.getDetail(supplierId);
	}

	public boolean insertOrUpdate(long userId, supplierEntity dataInsert) {
		dao =new supplierDAO();
		return dao.insertOrUpdate(userId, dataInsert);
	}

	public boolean delete(Long userId, String supplierId) {
		dao =new supplierDAO();
		return dao.delete(userId, supplierId);
	}
}
