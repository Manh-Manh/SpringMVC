package com.manhdn.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.discountEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.supplierEntity;

public class discountDAO {
	private CommonDatabase cmd;
	private Logger logger = Logger.getLogger(discountDAO.class);

	public discountDAO() {
		cmd = new CommonDatabase();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<discountEntity> findListDiscountByProductId(String productId) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		if(FunctionCommon.isEmpty(productId)) {
			logger.error(" id null ");
			return null;
		}
		sql.append(" SELECT * FROM `discount` d WHERE d.status = 1 AND d.discountId IN ( "
				+ "	SELECT pd.discountId FROM product_discount pd WHERE pd.productId = ? " + ")");
		sql.append(" AND d.endDate > SYSDATE() ");
		params.add(productId);
		
		List<discountEntity> lst = (List<discountEntity>) cmd.getListObjByParams(sql, params, discountEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + " Result size: " + lst.size());
			return null;
		}
		logger.info("Params: " + params + " Result size: " + lst.size());
		return lst;
	}

	public List<discountEntity> findDataList(Long userId, discountEntity dataSearch) {
		// TODO Auto-generated method stub
		List<discountEntity> result = new ArrayList<discountEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM discount d " + " WHERE (d.status != 0 or d.status is null) AND d.endDate > SYSDATE()  ");
		sql.append(" ORDER BY d.endDate ");
//		params.add(id);
		result = (List<discountEntity>) cmd.getListObjByParams(sql, params, discountEntity.class);
		logger.info("Params: " + params + " Result size: " + result.size());
		return result;
	}

	public boolean insertOrUpdate(Long userId, discountEntity discount) {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		if (userId == null || discount == null) {
			logger.error("Liên hệ quản trị hệ thống để được hỗ trợ " );
			return false;
		}
		if(discount.getDiscountId() == null) {
			String id = AppConstants.ID_DISCOUNT + (cmd.getMaxId("discount", "id") + 1);
			discount.setDiscountId(id);
		}
		boolean result = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		sql.append("INSERT INTO `discount` ( discountId, discount, "
				+ " startDate, endDate, status, description, created_date, "
				+ " updated_date,  created_by, updated_by ) "
				+ " VALUES ");
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");		
		sql.append("ON DUPLICATE KEY UPDATE "
				+ " discount = VALUES(discount), "
				+ " startDate = VALUES(startDate), "
				+ " endDate = VALUES(endDate), "
				+ " status = VALUES(status), "
				+ " description = VALUES(description), "

				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		params.add(discount.getDiscountId());
		params.add(discount.getDiscount());
		params.add(discount.getStartDate());
		params.add(discount.getEndDate());
		params.add(AppConstants.STATUS_ACTIVE);
		params.add(discount.getDescription());
		params.add(dateNow);
		params.add(dateNow);
		params.add(userId);
		params.add(userId);
		
		// insert or update product_discount
		logger.info("Params: " + params.toString() + " Result size: " + result);
		return result;
	}
	
	public boolean insertOrUpdateProductDiscount(Long userId, productEntity product) {
		List<Object> params2 = new ArrayList<Object>();
		StringBuilder sql2 = new StringBuilder();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());

		if (userId == null || product == null) {
			logger.error("Liên hệ quản trị hệ thống để được hỗ trợ " );
			return false;
		}
		sql2.append(" INSERT INTO `product_discount` (productId, discountId, status, "
				+ " created_date, updated_date, created_by, updated_by) "
				+ " VALUES ");
		if(FunctionCommon.isEmpty(product.getStrLstDiscount())) {
			logger.error("Liên hệ quản trị hệ thống để được hỗ trợ " );
			return false;
		}
		String []lstDiscountId = product.getStrLstDiscount().split(",");
		for (int i = 0; i < lstDiscountId.length; i++) {
			sql2.append("(?, ?, ?, ?, ?, ?, ?)");
			params2.add(product.getProductId());
			params2.add(lstDiscountId[i]);
			params2.add(AppConstants.STATUS_ACTIVE);
			params2.add(dateNow);
			params2.add(dateNow);
			params2.add(userId);
			params2.add(userId);
			if (i < lstDiscountId.length - 1) {
				sql2.append(" , ");
			}
		}

		sql2.append(" ON DUPLICATE KEY UPDATE " 
				+ " status = VALUES(status), " 
				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		boolean result2 = cmd.insertOrUpdateDataBase(sql2, params2);
		logger.info("Params: " + params2.toString() + " Result size: " + result2);
		return result2;
	}
}
