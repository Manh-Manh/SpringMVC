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

}
