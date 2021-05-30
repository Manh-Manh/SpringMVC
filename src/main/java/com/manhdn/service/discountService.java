package com.manhdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.discountDAO;
import com.manhdn.dao.productDAO;
import com.manhdn.dao.discountDAO;
import com.manhdn.entity.discountEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.discountEntity;

@Service
public class discountService {
	@Autowired
	discountDAO dao;
	
	/**
	 * 
	 * @param userId
	 * @param dataSearch
	 * @return
	 */
	public List<discountEntity> findDaList(Long userId, discountEntity dataSearch) {
		dao =new discountDAO();
		return dao.findDataList(userId, dataSearch);
	}

	
	public List<discountEntity> getAllDiscount() {
		dao =new discountDAO();
		return dao.getAllDiscount();
	}

	public discountEntity getDetail(String discountId) {
		dao =new discountDAO();
		return dao.getDetail(discountId);
	}

	public boolean insertOrUpdate(long userId, discountEntity dataInsert) {
		dao =new discountDAO();
		return dao.insertOrUpdate(userId, dataInsert);
	}

	public boolean delete(Long userId, String discountId) {
		dao =new discountDAO();
		return dao.delete(userId, discountId);
	}
}
