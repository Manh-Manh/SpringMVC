package com.manhdn.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.*;
import com.manhdn.mapper.productMapper;

@Repository
@Transactional
public class productDAO {

	/*
	 * Lay tat ca san pham
	 * 
	 * @return Lits<>
	 */
	public List<productEntity> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM products ");
		List<Object> params = new ArrayList<>();
		CommonDatabase cmd = new CommonDatabase();
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		productEntity pr = lst.get(0);
		
		return lst;
	}

	/*
	 * Chen ban ghi
	 */
	public boolean insert(productEntity product, Long userId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		if (userId != null) {
			product.setCreated_by(userId);
		}
		// date time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date =dtf.format(LocalDateTime.now());
		product.setCreated_date(date);
		
		sql.append("INSERT INTO products ( ");
		Gson gs = new Gson();
		JsonObject jso = new JsonObject();
		String jsonStr;
		jsonStr = gs.toJson(product);
		jso = gs.fromJson(jsonStr, JsonObject.class);
		Set<String> keys = jso.keySet();

		for (String i : keys) {
			
			sql.append(" "+i+", ");
		}
		
		return false;
	}

}
