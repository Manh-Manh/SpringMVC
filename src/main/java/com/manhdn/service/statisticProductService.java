package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.statisticProductDAO;
import com.manhdn.dao.statisticProductDAO;
import com.manhdn.entity.statisticProductEntity;

@Service
public class statisticProductService{
	@Autowired
	statisticProductDAO dao;
	
	/**
	 * 
	 * @param userId
	 * @param dataSearch
	 * @return
	 */
	public List<statisticProductEntity> findDaList(Long userId, statisticProductEntity dataSearch) {
		dao =new statisticProductDAO();
		return dao.findDataList(userId, dataSearch);
	}
	
//	public List<statisticProductEntity> getAllFace() {
//		dao =new statisticProductDAO();
//		return dao.getAllFace();
//	}
//
//	public statisticProductEntity getDetail(String statisticProductId) {
//		dao =new statisticProductDAO();
//		return dao.getDetail(statisticProductId);
//	}
//
//	public boolean insertOrUpdate(long userId, statisticProductEntity dataInsert) {
//		dao =new statisticProductDAO();
//		return dao.insertOrUpdate(userId, dataInsert);
//	}
//
//	public boolean delete(Long userId, String statisticProductId) {
//		dao =new statisticProductDAO();
//		return dao.delete(userId, statisticProductId);
//	}
}
