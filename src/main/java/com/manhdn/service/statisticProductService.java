package com.manhdn.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhdn.dao.statisticProductDAO;
import com.manhdn.dao.statisticProductDAO;
import com.manhdn.entity.statisticProductEntity;

@Service
public class statisticProductService{
	@Autowired
	statisticProductDAO dao;
	
	/**
	 * 
	 * @param userId
	 * @param dataSearch
	 * @return
	 */
	public List<statisticProductEntity> findDaList(Long userId, statisticProductEntity dataSearch) {
		dao =new statisticProductDAO();
		return dao.findDataList(userId, dataSearch);
	}
	
	public List<statisticProductEntity> findDaListByThisMonth(Long userId) {
		statisticProductEntity dataSearch = new statisticProductEntity();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		dataSearch.setToDate(dateNow);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			Date date1 = format.parse(dateNow);
			c.setTime(date1);
//			c.add(Calendar.MONTH, -1);
			c.set(Calendar.DAY_OF_MONTH, 1);
			String dateLast = format.format(c.getTime());
			dataSearch.setFromDate(dateLast);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao =new statisticProductDAO();
		return dao.findDataList(userId, dataSearch);
	}
	
//	public List<statisticProductEntity> getAllFace() {
//		dao =new statisticProductDAO();
//		return dao.getAllFace();
//	}
//
//	public statisticProductEntity getDetail(String statisticProductId) {
//		dao =new statisticProductDAO();
//		return dao.getDetail(statisticProductId);
//	}
//
//	public boolean insertOrUpdate(long userId, statisticProductEntity dataInsert) {
//		dao =new statisticProductDAO();
//		return dao.insertOrUpdate(userId, dataInsert);
//	}
//
//	public boolean delete(Long userId, String statisticProductId) {
//		dao =new statisticProductDAO();
//		return dao.delete(userId, statisticProductId);
//	}
}
