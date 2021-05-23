package com.manhdn.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.manhdn.FunctionCommon;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.orderEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.statisticProductEntity;
import com.manhdn.service.orderService;
import com.mysql.cj.xdevapi.Collection;
import com.manhdn.entity.statisticProductEntity;

@Repository
public class statisticProductDAO {
	CommonDatabase cmd;
	private Logger logger = Logger.getLogger(orderDAO.class);
	public statisticProductDAO(){
		cmd = new CommonDatabase();
	}	
	
	public List<statisticProductEntity> findDataList(Long userId, statisticProductEntity dataSearch) {
		List<statisticProductEntity> result = new ArrayList<statisticProductEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		orderEntity order = new orderEntity();
		
		if(null != dataSearch) {
			order.setFromDate(dataSearch.getFromDate());
			order.setToDate(dataSearch.getToDate());
		}
		orderService orderS = new orderService();
		/* Test */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		order.setStatus(3L);
		order.setToDate(dateNow);
		order.setFromDate("2021-04-23");
//		Calendar c = Calendar.getInstance();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");  
//		try {
//			Date date1 = format.parse(dateNow);
//			c.setTime(date1);
//			c.roll(Calendar.MONTH, -1);
//			String dateLast = format.format(c.getTime());
//			order.setFromDate(dateLast);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		List<orderEntity> listOrder = orderS.doSearchOrder(order);
		/* End Test */
		
		/* Main */
		
		// Loc danh sach hoa don theo ngay
//		List<orderEntity> listOrder = orderS.doSearchOrder(order);
		List<String> listProductId =new ArrayList<String>();
		// map Luu lai product; key = productId value = productEntity
		Map<String, productEntity> mapProduct = new HashMap<String, productEntity>();
		// map luu lai hoa don; key = orderI, value = orderE
		Map<String, orderEntity> mapOrder = new HashMap<String, orderEntity>();
		// map luu lai hoa don productId --> orderId (s)
		Map<String, List<String>> mapProductOrder = new HashMap<String, List<String>>();
		
		List<String> listOrderId = new ArrayList<String>();
		if(!FunctionCommon.isEmpty(listOrder)) {
			for(orderEntity o : listOrder) {
				mapOrder.put(o.getOrderId(), o);
				listOrderId.add(o.getOrderId());
				if(!FunctionCommon.isEmpty(o.getListProduct())) {
					for(productEntity p : o.getListProduct()) {
						String prId = p.getProductId();
						if(mapProduct.get(prId) == null) {
							mapProduct.put(prId, p);
						}
						if(mapProductOrder.get(prId) == null ) {
							List<String> lst = new ArrayList<String>();
							lst.add(o.getOrderId());
							mapProductOrder.put(prId, lst);
						
						}else {
							List<String> lst = mapProductOrder.get(prId);
							if(!lst.contains(o.getOrderId())) {
								lst.add(o.getOrderId());
								mapProductOrder.put(prId, lst);
							}
						}
					}
				}
			}
		}
		result = new ArrayList<statisticProductEntity>();
		if(mapProduct.size()==0) {
			return null;
		}
		
		for(String key : mapProduct.keySet()) {
			
			productEntity p = mapProduct.get(key);
			statisticProductEntity st = new statisticProductEntity();
			st.setProduct(p);
			List<orderEntity> lstOrder = new ArrayList<orderEntity>();
			for(String s : mapProductOrder.get(p.getProductId())) {
				lstOrder.add(mapOrder.get(s));
			}
			st.setListOrder(lstOrder);
			// Tinh tong so luong san pham ban dc
			Long quan = 0L;
			for(orderEntity o: lstOrder) {
				for(productEntity product : o.getListProduct()) {
					if(key.equals(product.getProductId()))
					{
						quan += product.getCartQuantity();
					}
					
				}
			}
			st.setQuantity(quan);
			result.add(st);
		}
				
		if(!FunctionCommon.isEmpty(result)) {
		}
		logger.info("Params: " + params +  "Result size: " + result.size());
		Collections.sort(result);
		return result;
	}

}
