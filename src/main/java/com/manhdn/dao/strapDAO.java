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
import com.manhdn.entity.strapEntity;
import com.manhdn.entity.strapEntity;
import com.manhdn.entity.productEntity;

@Repository
public class strapDAO {
	private CommonDatabase cmd;
	private Logger logger = Logger.getLogger(strapDAO.class);
	public strapDAO() {
		cmd = new CommonDatabase();
	}

	public strapEntity findstrapById(String id) {
		strapEntity result = new strapEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM strap s " + " WHERE s.strapId = ? and (s.status != 0 or s.status is null) ");
		params.add(id);
		List<strapEntity> lst = (List<strapEntity>) cmd.getListObjByParams(sql, params, strapEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		result = lst.get(0);
		logger.info("Params: " + params + "Result: " + lst);
		return result;
	}

	public List<strapEntity> findDataList(Long userId, productEntity dataSearch) {
		// TODO Auto-generated method stub
		List<strapEntity> result = new ArrayList<strapEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM strap s " + " WHERE 1 ");

		result = (List<strapEntity>) cmd.getListObjByParams(sql, params, strapEntity.class);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}

	public List<strapEntity> getAllStrap() {
		List<strapEntity>  result = new ArrayList<strapEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM strap s "
				+ " WHERE 1 ORDER BY s.strapId " );
//				+ "(f.status != 0 or f.status is null) ");
		result = (List<strapEntity>) cmd.getListObjByParams(sql, params, strapEntity.class);
		return result;
	}

	public strapEntity getDetail(String strapId) {
		StringBuilder sql = new StringBuilder();
		strapEntity result = new strapEntity();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM strap s " + "WHERE s.strapId = ");
		sql.append("?");
		params.add(strapId);
		List<strapEntity> lst = (List<strapEntity>) cmd.getListObjByParams(sql, params, strapEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + result);
			return null;
		}
		result = lst.get(0);
		logger.info("Params: " + params + "Result: " + result.toString());
		return result;
	}

	public boolean insertOrUpdate(Long userId, strapEntity dataInsert) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		dataInsert.setCreated_date(dateNow);
		dataInsert.setUpdated_date(dateNow);
		if(FunctionCommon.isEmpty(dataInsert.getStrapId())) {
			String id = AppConstants.ID_STRAP + (cmd.getMaxId("strap", "id") +1);
			dataInsert.setStrapId(id);
		}
		sql.append("INSERT INTO `strap` ( strapId, strapName, materialStrap, "
				+ " description, status, created_date, updated_date, "
				+ " created_by, updated_by )"
				+ " VALUES ");
		
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
		sql.append("ON DUPLICATE KEY UPDATE "
				+ " strapName = VALUES(strapName), "
				+ " materialStrap = VALUES(materialStrap), "
				+ " description = VALUES(description), "
				+ " status = VALUES(status), "
				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		params.add(dataInsert.getStrapId());
		params.add(dataInsert.getStrapName());
		params.add(dataInsert.getMaterialStrap());
		params.add(dataInsert.getDescription());
		params.add(dataInsert.getStatus());
		params.add(dateNow);
		params.add(dateNow);
		params.add(userId);
		params.add(userId); 
		boolean result = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
}
