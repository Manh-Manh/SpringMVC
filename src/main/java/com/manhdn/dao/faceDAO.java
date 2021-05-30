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
import com.manhdn.entity.productEntity;
import com.manhdn.entity.faceEntity;

@Repository
public class faceDAO {
	private CommonDatabase cmd;
	Logger logger = Logger.getLogger(faceDAO.class);
	public faceDAO() {
		cmd = new CommonDatabase();
	}
	public List<faceEntity> getAllFace(){
		List<faceEntity>  result = new ArrayList<faceEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM face f "
				+ " WHERE (f.del_flag is null or f.del_flag != 1) ORDER BY f.id " );
//				+ "(f.status != 0 or f.status is null) ");
		result = (List<faceEntity>) cmd.getListObjByParams(sql, params, faceEntity.class);
		return result;
	}
	public faceEntity findFaceById(String id) {
		faceEntity result = new faceEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM face f "
				+ " WHERE f.faceId = ? and (f.status != 0 or f.status is null) and (f.del_flag is null or f.del_flag != 1) ");
		params.add(id);
		List<faceEntity> lst = (List<faceEntity>) cmd.getListObjByParams(sql, params, faceEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		result = lst.get(0);
		logger.info("Params: " + params + "Result: " + lst);
		return result;
	}

	public List<faceEntity> findDataList(Long userId, faceEntity dataSearch) {
		List<faceEntity>  result = new ArrayList<faceEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM face f "
				+ " WHERE (f.status != 0 or f.status is null) and (f.del_flag is null or f.del_flag != 1) ");
		result = (List<faceEntity>) cmd.getListObjByParams(sql, params, faceEntity.class);
		return result;
	}
	
	public List<faceEntity> getListFaceName() {
		List<faceEntity>  result = new ArrayList<faceEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT DISTINCT f.faceName FROM face f "
				+ " WHERE (f.del_flag is null or f.del_flag != 1) and (f.status != 0 or f.status is null) ");
		result = (List<faceEntity>) cmd.getListObjByParams(sql, params, faceEntity.class);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
	public faceEntity getDetail(String faceId) {
		StringBuilder sql = new StringBuilder();
		faceEntity result = new faceEntity();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM face f " + " WHERE (f.del_flag is null or f.del_flag != 1) and f.faceId = ");
		sql.append("?");
		params.add(faceId);
		List<faceEntity> lst = (List<faceEntity>) cmd.getListObjByParams(sql, params, faceEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + result);
			return null;
		}
		result = lst.get(0);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
	public boolean insertOrUpdate(long userId, faceEntity dataInsert) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		dataInsert.setCreated_date(dateNow);
		dataInsert.setUpdated_date(dateNow);
		if(FunctionCommon.isEmpty(dataInsert.getFaceId())) {
			String id = AppConstants.ID_FACE + (cmd.getMaxId("face", "id") +1);
			dataInsert.setFaceId(id);
		}
		sql.append("INSERT INTO `face` ( faceId, faceName, thickness, faceSize, glass, waterProof, "
				+ " description, status, created_date, updated_date, "
				+ " created_by, updated_by )"
				+ " VALUES ");
		
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
		sql.append("ON DUPLICATE KEY UPDATE "
				+ " faceName = VALUES(faceName), "
				+ " thickness = VALUES(thickness), "
				+ " faceSize = VALUES(faceSize), "
				+ " glass = VALUES(glass), "
				+ " waterProof = VALUES(waterProof), "
				+ " description = VALUES(description), "
				+ " status = VALUES(status), "
				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		params.add(dataInsert.getFaceId());
		params.add(dataInsert.getFaceName());
		params.add(dataInsert.getThickness());
		params.add(dataInsert.getFaceSize());
		params.add(dataInsert.getGlass());
		params.add(dataInsert.getWaterProof() != null ? dataInsert.getWaterProof() : "0");
		params.add(dataInsert.getDescription());
		params.add(dataInsert.getStatus());
		params.add(dataInsert.getCreated_date() != null ? dataInsert.getCreated_date() : "");
		params.add(dataInsert.getUpdated_date() != null ? dataInsert.getUpdated_date() : "");
		params.add(dataInsert.getCreated_by() != null ? dataInsert.getCreated_by() : 0);
		params.add(dataInsert.getUpdated_by() != null ? dataInsert.getUpdated_by() : 0); 
		boolean result = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
	public boolean delete(Long userId, String faceId) {
		if(null == userId) {
			logger.error("Id null ");
			return false;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		boolean  result = false;
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE `face` f SET f.del_flag = 1, "
				+ " updated_by = ? , f.updated_date = ? "
				+ " WHERE f.faceId = ? " );
		
		params.add(userId);
		params.add(dateNow);
		params.add(faceId);
		result = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
}
