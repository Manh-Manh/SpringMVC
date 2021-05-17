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
import com.manhdn.entity.faceEntity;
import com.manhdn.entity.machineEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.machineEntity;

@Repository
public class machineDAO {
	private CommonDatabase cmd;
	private Logger logger = Logger.getLogger(machineDAO.class);
	public machineDAO() {
		cmd = new CommonDatabase();
	}

	public machineEntity findMachineById(String id) {
		machineEntity result = new machineEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM machine m " + " WHERE m.machineId = ? and (m.status != 0 or m.status is null) ");
		params.add(id);
		List<machineEntity> lst = (List<machineEntity>) cmd.getListObjByParams(sql, params, machineEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		result = lst.get(0);
		logger.info("Params: " + params + "Result: " + lst);
		return result;
	}

	public List<machineEntity> findDataList(Long userId, productEntity dataSearch) {
		List<machineEntity> result = new ArrayList<machineEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM machine m " + " WHERE 1 ");

		result = (List<machineEntity>) cmd.getListObjByParams(sql, params, machineEntity.class);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}

	public machineEntity getDetail(String machineId) {
		StringBuilder sql = new StringBuilder();
		machineEntity result = new machineEntity();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM machine f " + "WHERE f.machineId = ");
		sql.append("?");
		params.add(machineId);
		List<machineEntity> lst = (List<machineEntity>) cmd.getListObjByParams(sql, params, machineEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + result);
			return null;
		}
		result = lst.get(0);
		logger.info("Params: " + params + "Result: " + result.toString());
		return result;
	}

	public List<machineEntity> getAllMachine() {
		List<machineEntity>  result = new ArrayList<machineEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM machine m "
				+ " WHERE 1 ORDER BY m.id " );
//				+ "(f.status != 0 or f.status is null) ");
		result = (List<machineEntity>) cmd.getListObjByParams(sql, params, machineEntity.class);
		return result;
	}

	public boolean insertOrUpdate(Long userId, machineEntity dataInsert) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		dataInsert.setCreated_date(dateNow);
		dataInsert.setUpdated_date(dateNow);
		if(FunctionCommon.isEmpty(dataInsert.getMachineId())) {
			String id = AppConstants.ID_FACE + (cmd.getMaxId("machine", "id") +1);
			dataInsert.setMachineId(id);
		}
		sql.append("INSERT INTO `machine` ( machineId, machineName,  "
				+ " description, status, created_date, updated_date, "
				+ " created_by, updated_by )"
				+ " VALUES ");
		
		sql.append("(?, ?, ?, ?, ?, ?, ?, ? ) ");
		sql.append("ON DUPLICATE KEY UPDATE "
				+ " machineName = VALUES(machineName), "
				+ " description = VALUES(description), "
				+ " status = VALUES(status), "
				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		params.add(dataInsert.getMachineId());
		params.add(dataInsert.getMachineName());
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

	public boolean delete(Long userId, String machineId) {
		if(null == userId) {
			logger.error("Id null ");
			return false;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		boolean  result = false;
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE `machine` m SET m.del_flag = 1, "
				+ " m.updated_by = ? , m.updated_date = ? "
				+ " WHERE m.machineId = ? " );
		
		params.add(userId);
		params.add(dateNow);
		params.add(machineId);
		result = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
}
