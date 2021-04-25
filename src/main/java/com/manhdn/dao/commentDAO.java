package com.manhdn.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.commentEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;

@Repository
public class commentDAO {
	private CommonDatabase cmd;

	public commentDAO() {
		cmd = new CommonDatabase();
	}
	public List<commentEntity> getAllCommetByProductId(String productId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM comment WHERE (status is null or status !=0) and productId = ? ");
		List<Object> params = new ArrayList<>();
		params.add(productId);
		List<commentEntity> lst = (List<commentEntity>) cmd.getListObjByParams(sql, params, commentEntity.class);
		if(lst!=null && lst.size()>0) {
			for(commentEntity c :lst) {
				userDAO uDAO =new userDAO();
				userEntity u = uDAO.findUserById(c.getUserId());
				c.setUser(u);
			}
		}
		return lst;
	}
}
