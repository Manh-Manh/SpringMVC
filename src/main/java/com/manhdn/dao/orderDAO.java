package com.manhdn.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.machineEntity;
import com.manhdn.entity.orderEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;
@Repository
public class orderDAO {
	CommonDatabase cmd;
	private Logger logger = Logger.getLogger(orderDAO.class);
	public orderDAO(){
		cmd = new CommonDatabase();
	}
	public List<orderEntity> findDataList(Long orderId, orderEntity dataSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<orderEntity> getAllOrder() {
		List<orderEntity> result = new ArrayList<orderEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM `order` o WHERE status > 1 ORDER BY o.status, updated_date ASC ");
		result = (List<orderEntity>) cmd.getListObjByParams(sql, params, orderEntity.class);
		if(!FunctionCommon.isEmpty(result)) {
			for(orderEntity o : result) {
				o.setListProduct(getOrderDetail(o));
				userDAO uD = new userDAO();
				o.setUser(uD.findUserById(o.getUserId()));
			}
		}
		return result;
	}
	public boolean insertOrUpdate(Long userId, orderEntity cart, Long status) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		if (userId == null || cart == null) {
			logger.error("Id or cart null: " + userId +  cart);
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
		for (int i = 0; i < cart.getListProduct().size(); i++) {
			productEntity p = cart.getListProduct().get(i);
			sql2.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			params2.add(cart.getOrderId());
			params2.add(p.getProductId());
			params2.add(p.getCartQuantity());
			params2.add(p.getUnitPrice());
			params2.add(p.getDiscount() != null ? p.getDiscount() : 0D);
			params2.add(1); // Status
			params2.add(dateNow);
			params2.add(dateNow);
			params2.add(userId);
			params2.add(userId);
			params2.add(0);
			if (i < cart.getListProduct().size() - 1) {
				sql2.append(" , ");
			}
		}
		sql2.append("ON DUPLICATE KEY UPDATE " + " quantity = VALUES(quantity), " + " unitPrice = VALUES(unitPrice), "
				+ " discount = VALUES(discount), " + " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		boolean result2 = cmd.insertOrUpdateDataBase(sql2, params2);

		// Update product quantity
		if (status == AppConstants.OS_ORDERED ) {
			productDAO prD = new productDAO();
			for (productEntity p : cart.getListProduct()) {
				p.setQuantity(p.getQuantity() - p.getCartQuantity());
				prD.insertOrUpdate(p.getCreated_by(), p);
			}

		} else if (status == AppConstants.OS_ORDER_CANCEL) {
			productDAO prD = new productDAO();
			for (productEntity p : cart.getListProduct()) {
				p.setQuantity(p.getQuantity() + p.getCartQuantity());
				prD.insertOrUpdate(p.getCreated_by(), p);
			}
		}
		logger.info("Params: " + params + params2 + "Result: " + result + result2);
		return (result&&result2);
	}
	
	public List<orderEntity> findOrderByUserId(Long userId, Long status) {

		if(userId == null) {
			logger.error("Id null"+userId);
			return null;
		}
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM `order` WHERE userId = ? ");
		params.add(userId);
		if(status != null) {
			sql.append(" and status = ? ");
			params.add(status);
		}

		sql.append(" ORDER BY status ");
		List<orderEntity> lst = (List<orderEntity>) cmd.getListObjByParams(sql, params, orderEntity.class);
		if (lst.size() > 0) {
			for(orderEntity o : lst) {
				
				// Tim chi tiet hoa don
				o.setListProduct(getOrderDetail(o));
				// them user
				userDAO uD = new userDAO();
				o.setUser(uD.findUserById(o.getUserId()));
//				
//				StringBuilder sql2 = new StringBuilder();
//				List<Object> params2 = new ArrayList<Object>();
//				sql2.append(" SELECT * FROM `products` p WHERE p.status = 1 AND p.productId IN ( "
//						+ "	SELECT od.productId FROM order_detail od WHERE od.orderId = ? " + ")");
//				params2.add(o.getOrderId());
//				List<productEntity> lstProduct = (List<productEntity>) cmd.getListObjByParams(sql2, params2,
//						productEntity.class);
//				if (lstProduct.size() > 0) {
//					o.setListProduct(lstProduct);
//				}
			}
//			return cart;
		} else {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		logger.info("Params: " + params + "Result: " + lst);	
		return lst;
	}
	public orderEntity findOrderByOrderId(String orderId) {
		if(FunctionCommon.isEmpty(orderId)) {
			logger.error("Id null:" + orderId);
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
				o.setListProduct(getOrderDetail(o));
				// them user
				userDAO uD = new userDAO();
				o.setUser(uD.findUserById(o.getUserId()));
			}
//			return cart;
		} else {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		logger.info("Params: " + params + "Result: " + lst);
		return lst.get(0);
	}
	
	/**
	 * Laasy chi tiet hoa don
	 * @param order
	 * @return
	 */
	public List<productEntity> getOrderDetail(orderEntity order) {
		if (order == null) {
			return null;
		}
		StringBuilder sql2 = new StringBuilder();
		List<Object> params2 = new ArrayList<Object>();
		sql2.append(" SELECT  p.id, p.productId, p.productName, p.quantity, p.supplierId, p.unitPrice, "
				+ " p.gender, p.status, p.strapId, "
				+ " p.faceId, p.machineId, p.material, p.otherFunc, p.image, p.description, p.del_flag, "
				+ " p.created_date, p.updated_date, p.deleted_date, " + " p.created_by, p.updated_by, p.deleted_by, "
				+ " od.orderId, od.quantity as cartQuantity, od.discount as discount "

				+ " FROM `products` p, ( "
				+ " SELECT od2.orderId, od2.productId, od2.quantity, od2.discount "
				+ " FROM order_detail od2  WHERE od2.orderId = ? ) od "
				+ " WHERE p.status = 1 and p.productId = od.productId  ");
//		
//				+ "	SELECT od.productId FROM order_detail od WHERE od.orderId = ? " + ")");

		params2.add(order.getOrderId());
		List<productEntity> lstProduct = (List<productEntity>) cmd.getListObjByParams(sql2, params2,
				productEntity.class);
//		if (lstProduct.size() > 0) {
//			for(orderEntity o : lstProduct) {
//				
//			}
//		}
		return lstProduct;
	}
	public List<orderEntity> doSearchOrder(orderEntity dataSearch) {
		List<orderEntity> result = new ArrayList<orderEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM `order` o WHERE status > 1 ");
		// Theo trang thai
		if(dataSearch.getStatus() != null) {
			sql.append(" AND o.status = ? ");
			params.add(dataSearch.getStatus());
		}
		// THeo ngay
		if(!FunctionCommon.isEmpty(dataSearch.getFromDate())&&!FunctionCommon.isEmpty(dataSearch.getToDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");  
			try {
				Date date1 = format.parse(dataSearch.getFromDate());
				Date date2 = format.parse(dataSearch.getToDate());
				String fDate = format.format(date1);
				String tDate =format.format(date2);
				sql.append(" AND ? < o.orderDate AND o.orderDate < ? ");
				params.add(fDate);
				params.add(tDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		sql.append(" ORDER BY o.status, updated_date ASC ");
		result = (List<orderEntity>) cmd.getListObjByParams(sql, params, orderEntity.class);
		if(!FunctionCommon.isEmpty(result)) {
			for(orderEntity o : result) {
				o.setListProduct(getOrderDetail(o));
				userDAO uD = new userDAO();
				o.setUser(uD.findUserById(o.getUserId()));
			}
		}
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
	public List<orderEntity> getListNewOrder() {
		orderEntity o =new orderEntity();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		o.setStatus(2L);
		o.setToDate(dateNow);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");  
		try {
			Date date1 = format.parse(dateNow);
			c.setTime(date1);
			c.roll(Calendar.MONTH, -1);
			String dateLast = format.format(c.getTime());
			o.setFromDate(dateLast);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.doSearchOrder(o);
	}

}
