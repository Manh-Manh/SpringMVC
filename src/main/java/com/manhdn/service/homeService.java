package com.manhdn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.productDAO;
import com.manhdn.dao.homeDAO;
import com.manhdn.entity.productEntity;

@Service
public class homeService {
	@Autowired
	homeDAO dao;
	
	/**
	 * 
	 * @param homeId
	 * @param dataSearch
	 * @return
	 */
	public Map<String,String> loadMenu() {
		dao =new homeDAO();
		return dao.loadMenu();
		
	}
}
