package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.productDAO;
import com.manhdn.dao.orderDAO;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.orderEntity;

@Service
public class orderService {
	@Autowired
	orderDAO dao;
	
	/**
	 * 
	 * @param orderId
	 * @param dataSearch
	 * @return
	 */
	public List<orderEntity> findDaList(Long orderId, productEntity dataSearch) {
		dao =new orderDAO();
		return dao.findDataList(orderId, dataSearch);
	}

	public List<orderEntity> getAllOrder() {
		dao =new orderDAO();
		return dao.getAllOrder();
	}
	
	public boolean insertOrUpdate(Long userId, orderEntity cart, Long status) {
		// TODO Auto-generated method stub
		dao = new orderDAO();
		return dao.insertOrUpdate(userId, cart, status);
		
	}

	public List<orderEntity> findOderByUserId(Long userId, Long status) {
		// TODO Auto-generated method stub
		dao = new orderDAO();
		return dao.findOrderByUserId(userId, status);
	}

	public orderEntity findOrderById(String orderId) {
		dao = new orderDAO();
		return dao.findOrderByOrderId(orderId);
	}

	public orderEntity findOrderByOrderId(String orderId) {
		dao = new orderDAO();
		return dao.findOrderByOrderId(orderId);
	}
}
