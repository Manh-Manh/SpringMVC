package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.commentDAO;
import com.manhdn.dao.orderDAO;
import com.manhdn.entity.commentEntity;
import com.manhdn.entity.orderEntity;
import com.manhdn.entity.productEntity;

@Service
public class commentService {
	@Autowired
	commentDAO dao;
	public List<commentEntity> getAllCommetByProductId(String productId) {
		dao =new commentDAO();
		return dao.getAllCommetByProductId(productId);
	}
}
