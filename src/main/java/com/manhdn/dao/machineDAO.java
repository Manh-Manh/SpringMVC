package com.manhdn.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.machineEntity;

@Repository
public class machineDAO {
	private CommonDatabase cmd;

	public machineDAO() {
		cmd = new CommonDatabase();
	}

	public machineEntity findMachineById(String id) {
		machineEntity result = new machineEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM machine m "
				+ " WHERE m.machineId = ? and (m.status != 0 or m.status is null) ");
		params.add(id);
		List<machineEntity> lst = (List<machineEntity>) cmd.getListObjByParams(sql, params, machineEntity.class);
		if (null == lst || lst.size() == 0) {
			return null;
		}
		result = lst.get(0);
		return result;
	}
}
