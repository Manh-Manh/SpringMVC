package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.productDAO;
import com.manhdn.dao.machineDAO;
import com.manhdn.entity.productEntity;
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
}
