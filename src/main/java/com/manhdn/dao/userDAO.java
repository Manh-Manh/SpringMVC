package com.manhdn.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.userEntity;
import com.mysql.cj.Query;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;

@Repository
public class userDAO {
	private CommonDatabase cmd;

	public userDAO() {
		cmd = new CommonDatabase();
	}

	public userEntity findFaceById(String id) {
		userEntity result = new userEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM users u " + " WHERE u.userId = ? and (u.status != 0 or u.status is null) ");
		params.add(id);
		List<userEntity> lst = (List<userEntity>) cmd.getListObjByParams(sql, params, userEntity.class);
		if (null == lst || lst.size() == 0) {
			return null;
		}
		result = lst.get(0);
		return result;
	}

	public List<userEntity> findDataList(Long userId, productEntity dataSearch) {
		List<userEntity> result = new ArrayList<userEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM users u " + " WHERE 1 ");
		result = (List<userEntity>) cmd.getListObjByParams(sql, params, userEntity.class);
		return result;
	}

	public userEntity login(userEntity dataSearch) {
		// TODO Auto-generated method stub
		userEntity result = new userEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM users u "
				+ " WHERE (u.status = 1 or status is null) ");
		if(dataSearch.getEmail()!=null) {
			sql.append("and  ( u.email = ? and u.password = ? )");
			params.add(dataSearch.getEmail());
			params.add(dataSearch.getPassword());
		}
		
		List<userEntity> lst = (List<userEntity>) cmd.getListObjByParams(sql, params, userEntity.class);
		if (lst != null && lst.size() > 0) {
			result = lst.get(0);
			return result;
		}else {
			return null;
		}

		
	}
}
