package com.manhdn.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.supplierEntity;

@Repository
public class supplierDAO {
	private CommonDatabase cmd;

	public supplierDAO() {
		cmd = new CommonDatabase();
	}

	public supplierEntity findSupplierById(String id) {
		supplierEntity result = new supplierEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM suppliers sup "
				+ " WHERE sup.supplierId = ? and (sup.status != 0 or sup.status is null) ");
		params.add(id);
		List<supplierEntity> lst = (List<supplierEntity>) cmd.getListObjByParams(sql, params, supplierEntity.class);
		if (null == lst || lst.size() == 0) {
			return null;
		}
		result = lst.get(0);
		return result;
	}
}
