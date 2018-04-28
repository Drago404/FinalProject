package com.db;

import java.sql.*;

public class DBConnection {

	private	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private	static final String DB_URL = "jdbc:mysql://localhost:3306/technomarket";
	private	static final String USER = "root";
	private	static final String PASS = "1234";
	private	static DBConnection db = null;
	private	static Connection conn = null;
	
	
	private DBConnection() {
		
	}
	
	public static DBConnection getInstance() {
		if(db == null) {
			db = new DBConnection();
		}
		return db;
	}

	public static Connection getConnection() throws SQLException {
	
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(DB_URL, USER, PASS);

		return conn;
	}
}


