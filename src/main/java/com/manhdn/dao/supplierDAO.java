package com.manhdn.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.supplierEntity;

@Repository
public class supplierDAO {
	private CommonDatabase cmd;
	private Logger logger = Logger.getLogger(supplierDAO.class);
	public supplierDAO() {
		cmd = new CommonDatabase();
	}
	
	public List<supplierEntity> getAllSupplier(){
		List<supplierEntity>  result = new ArrayList<supplierEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM suppliers f "
				+ " WHERE (f.del_flag is null or f.del_flag != 1) ORDER BY f.id " );
//				+ "(f.status != 0 or f.status is null) ");
		result = (List<supplierEntity>) cmd.getListObjByParams(sql, params, supplierEntity.class);
		return result;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public supplierEntity findSupplierById(String id) {
		supplierEntity result = new supplierEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM suppliers sup "
				+ " WHERE sup.supplierId = ? and (sup.status != 0 or sup.status is null) and (sup.del_flag is null or sup.del_flag != 1) ");
		params.add(id);
		List<supplierEntity> lst = (List<supplierEntity>) cmd.getListObjByParams(sql, params, supplierEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		result = lst.get(0);
		logger.info("Params: " + params + "Result: " + lst);
		return result;
	}

	public List<supplierEntity> findDataList(Long userId, supplierEntity dataSearch) {
		// TODO Auto-generated method stub
		List<supplierEntity> result = new ArrayList<supplierEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM suppliers sup "
				+ " WHERE (sup.status != 0 or sup.status is null) and (sup.del_flag is null or sup.del_flag != 1) ");
//		params.add(id);
		result = (List<supplierEntity>) cmd.getListObjByParams(sql, params, supplierEntity.class);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
	
	public supplierEntity getDetail(String supplierId) {
		StringBuilder sql = new StringBuilder();
		supplierEntity result = new supplierEntity();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM suppliers sup " + " WHERE (sup.del_flag is null or sup.del_flag != 1) and sup.supplierId = ");
		sql.append("?");
		params.add(supplierId);
		List<supplierEntity> lst = (List<supplierEntity>) cmd.getListObjByParams(sql, params, supplierEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + result);
			return null;
		}
		result = lst.get(0);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
	public boolean insertOrUpdate(long userId, supplierEntity dataInsert) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		dataInsert.setCreated_date(dateNow);
		dataInsert.setUpdated_date(dateNow);
		if(FunctionCommon.isEmpty(dataInsert.getSupplierId())) {
			String id = AppConstants.ID_SUPPLIER + (cmd.getMaxId("supplier", "id") +1);
			dataInsert.setSupplierId(id);
		}
		sql.append("INSERT INTO `suppliers` ( supplierId, supplierName, address, phoneNumber, "
				+ " email, website, location, logo, "
				+ " description, status, del_flag, created_date, updated_date, "
				+ " created_by, updated_by )"
				+ " VALUES ");
		
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
		sql.append("ON DUPLICATE KEY UPDATE "
				+ " supplierName = VALUES(supplierName), "
				+ " address = VALUES(address), "
				+ "phoneNumber = VALUES(phoneNumber), "
				+ " email = VALUES(email), "
				+ " website = VALUES(website), "
				+ " location = VALUES(location), "
				+ " logo = VALUES(logo), "
				
				+ " description = VALUES(description), "
				+ " status = VALUES(status), "
				+ " del_flag = VALUES(del_flag), "
				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		params.add(dataInsert.getSupplierId());
		params.add(dataInsert.getSupplierName());
		params.add(dataInsert.getAddress());
		params.add(dataInsert.getPhoneNumber());
		params.add(dataInsert.getEmail());
		params.add(dataInsert.getWebsite());
		params.add(dataInsert.getLocation());
		params.add(dataInsert.getLogo());
		params.add(dataInsert.getDescription());
		params.add(dataInsert.getStatus());
		params.add(dataInsert.getDel_flag()!=null?dataInsert.getDel_flag():0);
		params.add(dateNow);
		params.add(dateNow);
		params.add(userId);
		params.add(userId); 
		boolean result = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
	public boolean delete(Long userId, String supplierId) {
		if(null == userId) {
			logger.error("Id null ");
			return false;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		boolean  result = false;
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE `suppliers` f SET f.del_flag = 1, "
				+ " updated_by = ? , f.updated_date = ? "
				+ " WHERE f.supplierId = ? " );
		
		params.add(userId);
		params.add(dateNow);
		params.add(supplierId);
		result = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
}
