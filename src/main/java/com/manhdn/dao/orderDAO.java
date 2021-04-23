package com.manhdn.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.orderEntity;
import com.manhdn.entity.productEntity;
@Repository
public class orderDAO {
	CommonDatabase cmd;
	public orderDAO(){
		cmd = new CommonDatabase();
	}
	public List<orderEntity> findDataList(Long orderId, productEntity dataSearch) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public boolean insertOrUpdate(Long userId, orderEntity cart, Long status) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		if (userId == null || cart == null) {
			return false;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		// Insert or update Order
		if(cart.getOrderId() == null) {
			String orderID = AppConstants.ID_ODER + (cmd.getMaxId("order", "id") + 1);
			cart.setOrderId(orderID);
		}
		if (status != null) {
			cart.setStatus(status);
		}
		if (cart.getStatus() == null ) {
			cart.setStatus(1L);
		}
		sql.append("INSERT INTO `order` ( orderId, userId, status, created_date,"
				+ " updated_date, created_by, updated_by, del_flag) VALUES ");
		
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?)");		
		sql.append("ON DUPLICATE KEY UPDATE "
				+ " status = VALUES(status), "
				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		params.add(cart.getOrderId());
		params.add(userId);
		params.add(cart.getStatus() != null ? cart.getStatus() : "");
		params.add(dateNow);
		params.add(dateNow);
		params.add(userId);
		params.add(userId);
		params.add(0);		
		boolean result = cmd.insertOrUpdateDataBase(sql, params);
		
		// Insert or update Order detail
		List<Object> params2 = new ArrayList<Object>();
		StringBuilder sql2 = new StringBuilder();
		sql2.append(" INSERT INTO order_detail(orderId, productId, "
				+ " quantity, unitPrice, discount, status, created_date, "
				+ " updated_date, created_by, updated_by, del_flag) VALUES "
				+ "");
		for(int i =0;i< cart.getListProduct().size();i++) {
			productEntity p =cart.getListProduct().get(i);
			sql2.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");		
			params2.add(cart.getOrderId());
			params2.add(p.getProductId());
			params2.add(p.getCartQuantity());
			params2.add(p.getUnitPrice());
			params2.add(p.getDiscount() != null ? p.getDiscount() : 0D);
			params2.add(1); //Status
			params2.add(dateNow);
			params2.add(dateNow);
			params2.add(userId);
			params2.add(userId);
			params2.add(0);
			if (i < cart.getListProduct().size() - 1) {
				sql2.append(" , ");
			}
		}         
	
		sql2.append("ON DUPLICATE KEY UPDATE "
				+ " quantity = VALUES(quantity), "
				+ " unitPrice = VALUES(unitPrice), "
				+ " discount = VALUES(discount), "
				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		boolean result2 = cmd.insertOrUpdateDataBase(sql2, params2);
		
		
		return (result&&result2);
	}
	
	public List<orderEntity> findOrderByUserId(Long userId, Long osNoOrder) {

		if(userId == null) {
			return null;
		}
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM `order` WHERE userId = ? ");
		params.add(userId);
		if(osNoOrder != null) {
			sql.append(" and status = ? ");
			params.add(osNoOrder);
		}
		List<orderEntity> lst = (List<orderEntity>) cmd.getListObjByParams(sql, params, orderEntity.class);
		if (lst.size() > 0) {
			for(orderEntity o : lst) {
				// Tim chi tiet hoa don
				StringBuilder sql2 = new StringBuilder();
				List<Object> params2 = new ArrayList<Object>();
				sql2.append(" SELECT * FROM `products` p WHERE p.status = 1 AND p.productId IN ( "
						+ "	SELECT od.productId FROM order_detail od WHERE od.orderId = ? " + ")");
				params2.add(o.getOrderId());
				List<productEntity> lstProduct = (List<productEntity>) cmd.getListObjByParams(sql2, params2,
						productEntity.class);
				if (lstProduct.size() > 0) {
					o.setListProduct(lstProduct);
				}
			}
//			return cart;
		} else {
			return null;
		}
	return lst;
	}
	public orderEntity findOrderByUserId(String orderId) {
		if(FunctionCommon.isEmpty(orderId)) {
			return null;
		}
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM `order` WHERE orderId = ? ");
		params.add(orderId);
		List<orderEntity> lst = (List<orderEntity>) cmd.getListObjByParams(sql, params, orderEntity.class);
		if (lst.size() > 0) {
			for (orderEntity o : lst) {
				// Tim chi tiet hoa don
				StringBuilder sql2 = new StringBuilder();
				List<Object> params2 = new ArrayList<Object>();
				sql2.append(" SELECT * FROM `products` p WHERE p.status = 1 AND p.productId IN ( "
						+ "	SELECT od.productId FROM order_detail od WHERE od.orderId = ? " + ")");
				params2.add(o.getOrderId());
				List<productEntity> lstProduct = (List<productEntity>) cmd.getListObjByParams(sql2, params2,
						productEntity.class);
				if (lstProduct.size() > 0) {
					o.setListProduct(lstProduct);
				}
			}
//			return cart;
		} else {
			return null;
		}
		return lst.get(0);
	}
	

}
