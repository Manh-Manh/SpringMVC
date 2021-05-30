package com.manhdn.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.roleEntity;
import com.manhdn.entity.userEntity;

@Repository
public class roleDAO {
	private CommonDatabase cmd;
	private Logger logger = Logger.getLogger(roleDAO.class);
	public roleDAO() {
		cmd = new CommonDatabase();
		
	}
	public List<roleEntity> getListRoleByUser(userEntity user) {
		List<roleEntity> result = new ArrayList<roleEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT * FROM `role` r WHERE r.roleId IN ( "
				+ "	SELECT ur.roleId FROM user_role ur WHERE ur.userId  = ? )");
		params.add(user.getUserId());
		result = (List<roleEntity>) cmd.getListObjByParams(sql, params, roleEntity.class);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	
	
	}

}
