package com.manhdn.database;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.manhdn.AppConstants;
import com.manhdn.entity.productEntity;

//import com.mysql.jdbc.PreparedStatement;

public class CommonDatabase {

	/*
	 * Get List object by params[]
	 * 
	 * @return List< Obj>
	 */
	private Connection conn;
	MySQLConnector con;
	ResultSet rs = null;

	/*
	 * Ham mo ket noi
	 */
	private Connection openConnection() {
		con = new MySQLConnector();
		try {
			return con.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Ham dong ket noi
	 */
	private void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<? extends Object> getListObjByParams(StringBuilder sql, List<Object> params, Class<?> T) {
//		List<T> listResult = new ArrayList<T>();
		conn = openConnection();
		Field fieldlistSuper[] = T.getSuperclass().getDeclaredFields();
		Field fieldlist[] = T.getDeclaredFields();

		JsonObject json;
		List<Object> resultObj = new ArrayList();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(sql.toString());
			if (params != null && params.size() > 0) {
				// add dieu kien vao cau lenh sql
				for (int i = 0; i < params.size(); i++) {
					Object object = params.get(i);
					preparedStatement.setObject(i + 1, object);
				}
			}
			rs = preparedStatement.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					JsonObject datasetItem = new JsonObject();
					for (int i = 0; i < fieldlist.length; i++) {
						// thuc hien add du lieu vao json
						Field field = fieldlist[i];
						String fileName = field.getName();
//						ResultSetMetaData metaData = rs.getMetaData();
//						assertEquals("VAL", metaData.getColumnName(1));
//					      assertEquals(1, rs.getInt("VAL"));
//						
//						
						if (hasColumn(rs, fileName)) {
							datasetItem.addProperty(fileName, rs.getString(fileName));
						}
					}
					// Super
					for (int i = 0; i < fieldlistSuper.length; i++) {
						// thuc hien add du lieu vao json
						Field field = fieldlistSuper[i];
						String fileName = field.getName();
						if (hasColumn(rs, fileName)) {
							datasetItem.addProperty(fileName, rs.getString(fileName));
						}
					}

					GsonBuilder builder = new GsonBuilder();
					Gson gson = builder.create();
					Object item = gson.fromJson(datasetItem, T);
					resultObj.add(item);
				}
			}
			closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultObj;
	}

	/*
	 * 
	 */
	public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();

		int columns = rsmd.getColumnCount();
		for (int x = 1; x <= columns; x++) {
//			String cl = rsmd.getColumnName(x);
			String cl = rsmd.getColumnLabel(x);
			if (columnName.toLowerCase().equals(cl.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Chen hoac cap nhat ban ghi
	 *
	 * @param queryString Cau truy van
	 * @param arrParams   Tham so
	 * @return
	 */
	public synchronized Boolean insertOrUpdateDataBase(StringBuilder queryString, List<Object> arrParams) {

		Boolean valueResult = false;
		try {

			conn = openConnection();
			PreparedStatement updateSqlStatement = conn.prepareStatement(queryString.toString());
			if (arrParams != null && arrParams.size() > 0) {
				// add dieu kien vao cau lenh sql
				for (int i = 0; i < arrParams.size(); i++) {
					Object object = arrParams.get(i);
					updateSqlStatement.setObject(i + 1, object);
				}
			}
//            updateSqlStatement.setQueryTimeout(
//                    );
			int resultInsert = updateSqlStatement.executeUpdate();
			if (resultInsert > 0 || resultInsert == -2) {
				valueResult = true;
			}

			updateSqlStatement.close();

		} catch (Exception ex) {
			System.out.println("insertOrUpdateDataBase(Chen hoac cap nhat ban ghi) - " + "Loi: "
					+ queryString + "\nparams: " + arrParams + ex);
		} finally {
			closeConnection();
		}
		return valueResult;
	}
	/**
	 * 
	 * @param tableName ten bang
	 * @param idKey Ten khoa chinh
	 * @return
	 * @throws SQLException 
	 */
	public synchronized Long getMaxId(String tableName, String idKey) {
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT " + idKey + " FROM `" + tableName + "` ORDER BY " + idKey + " desc LIMIT 0,1");
		conn = openConnection();
		Long result = 0L;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
			rs = preparedStatement.executeQuery();
			rs.first();
			result = Long.valueOf(rs.getString(idKey));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		closeConnection();
		return result;
		
	}
}
