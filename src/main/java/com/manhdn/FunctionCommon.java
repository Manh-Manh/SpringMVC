package com.manhdn;

import java.util.List;

public class FunctionCommon {

	/**
	 * ham thuc hien select sql theo dang like string
	 *
	 * @param strColumn
	 * @return
	 */
	public static String generateSqlLike(String strColumn, String strSearch) {
		StringBuilder strSqlLike = new StringBuilder();
		if (isEmpty(strColumn) || isEmpty(strSearch)) {
			return "";
		}
		strSqlLike.append(String.format(" REPLACE(lower(%s),'", strColumn));
		strSqlLike.append("" + AppConstants.strSPEC.charAt(0));
		strSqlLike.append("', '");
		strSqlLike.append("" + AppConstants.strREPL.charAt(0));
		strSqlLike.append("') ");

		for (int i = 1; i < AppConstants.strSPEC.length(); i++) {
			strSqlLike.insert(0, "REPLACE(");
			strSqlLike.append(",'" + AppConstants.strSPEC.charAt(i));
			strSqlLike.append("', '");
			strSqlLike.append("" + AppConstants.strREPL.charAt(i));
			strSqlLike.append("') ");
		}
		strSqlLike.append(String.format(" like %s ", "'%" + strSearch.toLowerCase() + "%'"));

		return strSqlLike.toString();
	}

	public static String generateSqlIn(String strColumn, List<? extends Object> list) {
		StringBuilder strSqlIn = new StringBuilder();
		if (isEmpty(strColumn) || isEmpty(list)) {
			return "";
		}
		strSqlIn.append(" " + strColumn + " IN ( ");
		for (int i = 0; i < list.size(); i++) {
			strSqlIn.append(" ?");
			if (i < list.size() - 1) {
				strSqlIn.append(",");
			}			
		}
		strSqlIn.append(" ) ");
		return strSqlIn.toString();
	}

	/**
	 * kiem tra chuoi rong
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	public static boolean isEmpty(List<? extends Object> lst) {
		return (lst == null || lst.size() == 0);
	}
}
