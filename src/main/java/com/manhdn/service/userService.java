package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.productDAO;
import com.manhdn.dao.userDAO;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;

@Service
public class userService {
	@Autowired
	userDAO dao;
	
	/**
	 * 
	 * @param userId
	 * @param dataSearch
	 * @return
	 */
	public List<userEntity> findDaList(Long userId, productEntity dataSearch) {
		dao =new userDAO();
		return dao.findDataList(userId, dataSearch);
	}

	public userEntity login(userEntity dataSearch) {
		// TODO Auto-generated method stub
		dao =new userDAO();
		return dao.login(dataSearch);
		
	}
}
