package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.productDAO;
import com.manhdn.dao.faceDAO;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.faceEntity;

@Service
public class faceService {
	@Autowired
	faceDAO dao;
	
	/**
	 * 
	 * @param userId
	 * @param dataSearch
	 * @return
	 */
	public List<faceEntity> findDaList(Long userId, productEntity dataSearch) {
		dao =new faceDAO();
		return dao.findDataList(userId, dataSearch);
	}
}
