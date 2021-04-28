package com.manhdn.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
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

	public faceEntity findFaceById(String id) {
		faceEntity result = new faceEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM face f "
				+ " WHERE f.faceId = ? and (f.status != 0 or f.status is null) ");
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

	public List<faceEntity> findDataList(Long userId, productEntity dataSearch) {
		List<faceEntity>  result = new ArrayList<faceEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM face f "
				+ " WHERE (f.status != 0 or f.status is null) ");
		result = (List<faceEntity>) cmd.getListObjByParams(sql, params, faceEntity.class);
		return result;
	}
	
	public List<faceEntity> getListFaceName() {
		List<faceEntity>  result = new ArrayList<faceEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT DISTINCT f.faceName FROM face f "
				+ " WHERE (f.status != 0 or f.status is null) ");
		result = (List<faceEntity>) cmd.getListObjByParams(sql, params, faceEntity.class);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
}
