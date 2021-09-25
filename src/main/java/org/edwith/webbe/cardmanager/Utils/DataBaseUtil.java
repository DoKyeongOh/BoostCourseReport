package org.edwith.webbe.cardmanager.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseUtil {
	private Connection conn = null;
	private String DataBaseClassName = "com.mysql.cj.jdbc.Driver";

	public DataBaseUtil() {
		conn = null;
	}

	private String getConnection(String url, String id, String pswd) {
		// MySQL 8.0 이상부터 타임존을 지정해주어야 접속할 수 있게 변경됨.
		url = url + "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Class.forName(DataBaseClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Driver Load Fail ---";
		}

		try {
			conn = DriverManager.getConnection(url, id, pswd);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Connection Get Fail ---";
		}

		return "";
	}

	public boolean Connect(String dbAddr, String dbName, String userId, String userPw) {
		String url = "jdbc:mysql://" + dbAddr + "/" + dbName;
		if (dbName == "") {
			if (Connect(dbAddr, userId, userPw)) return true;
			else return false;
		}
		
		if (getConnection(url, userId, userPw) == "")
			return true;
		else
			return false;
	}

	public boolean Connect(String dbAddr, String userId, String userPw) {
		String url = "jdbc:mysql://" + dbAddr + "?useSSL=false";
		
		if (getConnection(url, userId, userPw) == "")
			return true;
		else
			return false;
	}
	
	public PreparedStatement getStatement (PreparedStatement ps, String sql) {
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public void close() {
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}