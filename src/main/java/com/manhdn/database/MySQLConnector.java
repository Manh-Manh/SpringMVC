
package com.manhdn.database;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 */
public class MySQLConnector {
	// Khai bao JDBC driver
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// Khai bao database url
	static final String DB_URL = "jdbc:mysql://localhost:3306/web_dong_ho?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	// Tai khoan, mat khau database
	static final String USER = "admin";
	static final String PASS = "222222a@";

	/**
	 * 
	 */
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return DriverManager.getConnection(DB_URL, USER, PASS);

	}
}
