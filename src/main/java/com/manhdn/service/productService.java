package com.manhdn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manhdn.dao.productDAO;
import com.manhdn.entity.ajaxEntity;
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
//		prDAO.findAll();
		return prDAO.findDataList(userId, dataSearch);
	}
	
	public List<productEntity> findDaList(Long userId, Map<String, List<String>> mapSearch, Integer page, Integer pageSize) {
		prDAO =new productDAO();
//		prDAO.findAll();
		return prDAO.findDataList(userId, mapSearch, page, pageSize);
	}

	public productEntity getProductDetail(String id) {
		// TODO Auto-generated method stub
		prDAO =new productDAO();
		return prDAO.getProductDetail(id);
	}

	public boolean insertOrUpdate(long userId, productEntity dataSearch) {
		// TODO Auto-generated method stub
		prDAO = new productDAO();
		return prDAO.insertOrUpdate(userId,dataSearch);
	}

	public List<productEntity> quickSearch(Long userId, String strSearch) {
		// TODO Auto-generated method stub
		prDAO = new productDAO();
		return prDAO.quickSearch(userId,strSearch);
	}

	public Integer countDataList(Long userId, Map<String, List<String>> mapSearch) {
		// TODO Auto-generated method stub
		prDAO = new productDAO();
		return prDAO.countDataList(userId,mapSearch);
	}

	public List<productEntity> findListSale() {
		// TODO Auto-generated method stub
		prDAO = new productDAO();
		return prDAO.findListSale();
	}

	public List<productEntity> finListNew() {
		// TODO Auto-generated method stub
		prDAO = new productDAO();
		return prDAO.findListNew();
	}

	public List<productEntity> findListSuggest(productEntity dataSelected) {
		// TODO Auto-generated method stub
		prDAO = new productDAO();
		return prDAO.findListSuggest(dataSelected);
	}

	public boolean delete(Long userId, String productId) {
		prDAO = new productDAO();
		return prDAO.delete(userId, productId);
	}

//	public List<productEntity> insert(Long userId, productEntity dataInsert) {
//		// TODO Auto-generated method stubv
//		prDAO = new productDAO();
//		prDAO.insert(userId,dataSearch);
//	}

}
