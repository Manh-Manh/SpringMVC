package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.productDAO;
import com.manhdn.dao.faceDAO;
import com.manhdn.dao.machineDAO;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.faceEntity;
import com.manhdn.entity.machineEntity;

@Service
public class machineService {
	@Autowired
	machineDAO dao;
	
	/**
	 * 
	 * @param userId
	 * @param dataSearch
	 * @return
	 */
	public List<machineEntity> findDaList(Long userId, productEntity dataSearch) {
		dao =new machineDAO();
		return dao.findDataList(userId, dataSearch);
	}

	public machineEntity getDetail(String machineId) {
		dao =new machineDAO();
		return dao.getDetail(machineId);
	}
	public List<machineEntity> getAllMachine() {
		dao =new machineDAO();
		return dao.getAllMachine();
	}

	public boolean insertOrUpdate(Long userId, machineEntity dataInsert) {
		// TODO Auto-generated method stub
		dao =new machineDAO();
		return dao.insertOrUpdate(userId, dataInsert);
	}

	public boolean delete(Long userId, String machineId) {
		dao =new machineDAO();
		return dao.delete(userId, machineId);
	}
}
