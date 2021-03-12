package com.manhdn.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
//import com.manhdn.dao.*;
import com.manhdn.entity.productEntity;
public class productMapper implements RowMapper<productEntity>{
	public productEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		productEntity customer = new productEntity();
		customer.setId(rs.getString("id"));
		customer.setProductName(rs.getString("productName"));
		customer.setQuantity(rs.getLong("quantity"));
		return customer;
	}
}
