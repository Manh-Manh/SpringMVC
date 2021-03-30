package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manhdn.dao.productDAO;
import com.manhdn.entity.productEntity;
@Service
@Transactional
public class productService {

	@Autowired(required = false)
	private productDAO prDAO;
	
	public List<productEntity> findAll() {
		prDAO =new productDAO();
		return prDAO.findAll();
	}
	
	public List<productEntity> findDaList(Long userId, productEntity dataSearch) {
		prDAO =new productDAO();
		return prDAO.findDataList(userId, dataSearch);
	}

	public productEntity getProductDetail(String id) {
		// TODO Auto-generated method stub
		prDAO =new productDAO();
		return prDAO.getProductDetail(id);
	}

	public void insertOrUpdate(long userId, productEntity dataSearch) {
		// TODO Auto-generated method stub
		prDAO = new productDAO();
		prDAO.insert(userId,dataSearch);
	}

}
