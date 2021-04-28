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
import com.manhdn.entity.userEntity;
import com.mysql.cj.Query;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;

@Repository
public class userDAO {
	private CommonDatabase cmd;
	private Logger logger = Logger.getLogger(userDAO.class);
	public userDAO() {
		cmd = new CommonDatabase();
	}

	public userEntity findUserById(Long id) {
		userEntity result = new userEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM users u " + " WHERE u.userId = ? and (u.status != 0 or u.status is null) ");
		params.add(id);
		List<userEntity> lst = (List<userEntity>) cmd.getListObjByParams(sql, params, userEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		result = lst.get(0);
		logger.info("Params: " + params + "Result: " + lst);
		return result;
	}

	public List<userEntity> findDataList(Long userId, productEntity dataSearch) {
		List<userEntity> result = new ArrayList<userEntity>();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM users u " + " WHERE 1 ");
		result = (List<userEntity>) cmd.getListObjByParams(sql, params, userEntity.class);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}

	public userEntity login(userEntity dataSearch) {
		// TODO Auto-generated method stub
		userEntity result = new userEntity();
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		
		if (FunctionCommon.isEmpty(dataSearch.getUserName())) {
			logger.error("data null: " + dataSearch);
			return null;
		}
		dataSearch.setEmail(dataSearch.getUserName());
		sql.append("SELECT * FROM users u "
				+ " WHERE (u.status = 1 or status is null) ");

		sql.append(" AND  ( ( u.email = ? and u.password = ? ) OR ( u.userName = ? and u.password = ? ) )");
		params.add(dataSearch.getUserName());
		params.add(dataSearch.getPassword());
		params.add(dataSearch.getUserName());
		params.add(dataSearch.getPassword());
		
		List<userEntity> lst = (List<userEntity>) cmd.getListObjByParams(sql, params, userEntity.class);
		if (lst != null && lst.size() > 0) {
			result = lst.get(0);
			logger.info("Params: " + params + "Result: " + lst);
			return result;
		}else {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}

		
	}

	public boolean insertOrUpdate(Long userId, userEntity user) {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		user.setCreated_date(dateNow);
		user.setUpdated_date(dateNow);
		if(user.getUserId()==null) {
			Long id = cmd.getMaxId("users", "id") +1;
			user.setUserId(id);
		}
		sql.append("INSERT INTO `users` ( userId, userName, " 
				+ " fullName, password, email, address, phoneNumber, status, "
				+ " avatar, birthDate, del_flag, created_date, updated_date, "
				+ " created_by, updated_by )"
//				+ " birthDate ) "
				+ " VALUES ");
		
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
//		sql.append(""+ user.getBirthDate() != null ? (" '"+user.getBirthDate()+"' ") : null);
//		sql.append(" ) ");
		sql.append("ON DUPLICATE KEY UPDATE "
				+ " fullName = VALUES(fullName), "
				+ " password = VALUES(password), "
				+ " email = VALUES(email), "
				+ " address = VALUES(address), "
				+ " phoneNumber = VALUES(phoneNumber), "
				+ " status = VALUES(status), "
				+ " avatar = VALUES(avatar), "
				+ " birthDate = VALUES(birthDate), "
				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		params.add(user.getUserId() != null ? user.getUserId() : "");
		params.add(user.getUserName() != null ? user.getUserName()
				: AppConstants.ID_USER + (cmd.getMaxId("users", "id") + 1));
		params.add(user.getFullName() != null ? user.getFullName() : "");
		params.add(user.getPassword() != null ? user.getPassword() : "123");
		params.add(user.getEmail() != null ? user.getEmail() : "");
		params.add(user.getAddress() != null ? user.getAddress() : "");
		params.add(user.getPhoneNumber() != null ? user.getPhoneNumber() : "");
		params.add(user.getStatus() != null ? user.getStatus() : AppConstants.STATUS_ACTIVE);
		params.add(user.getAvatar() != null ? user.getAvatar() : "default-avatar.png");
		params.add(user.getBirthDate() != null ? user.getBirthDate() : dateNow);
		params.add(user.getDel_flag() != null ? user.getDel_flag() : 0);
		params.add(user.getCreated_date() != null ? user.getCreated_date() : "");
		params.add(user.getUpdated_date() != null ? user.getUpdated_date() : "");
		params.add(user.getCreated_by() != null ? user.getCreated_by() : 0);
		params.add(user.getUpdated_by() != null ? user.getUpdated_by() : 0); 
		boolean result = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
}
