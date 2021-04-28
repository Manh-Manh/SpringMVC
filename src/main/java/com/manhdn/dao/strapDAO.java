package com.manhdn.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.manhdn.database.CommonDatabase;
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

		sql.append("SELECT * FROM strap m " + " WHERE m.strapId = ? and (m.status != 0 or m.status is null) ");
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
}
