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
	public List<faceEntity> findDaList(Long userId, faceEntity dataSearch) {
		dao =new faceDAO();
		return dao.findDataList(userId, dataSearch);
	}
	
	public List<faceEntity> getAllFace() {
		dao =new faceDAO();
		return dao.getAllFace();
	}

	public faceEntity getDetail(String faceId) {
		dao =new faceDAO();
		return dao.getDetail(faceId);
	}

	public boolean insertOrUpdate(long userId, faceEntity dataInsert) {
		dao =new faceDAO();
		return dao.insertOrUpdate(userId, dataInsert);
	}

	public boolean delete(Long userId, String faceId) {
		dao =new faceDAO();
		return dao.delete(userId, faceId);
	}
}
