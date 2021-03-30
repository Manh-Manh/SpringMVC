package com.manhdn.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.faceEntity;

@Repository
public class faceDAO {
	private CommonDatabase cmd;

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
			return null;
		}
		result = lst.get(0);
		return result;
	}
}
