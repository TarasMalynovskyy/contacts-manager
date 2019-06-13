package com.ivvysoft.cm.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataBaseConnection {

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	private static final String DB_URL = "jdbc:mariadb://localhost:3306/contacts_manager";
	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "";

	private static DataBaseConnection instance = null;
	private static Connection conn = null;

	private DataBaseConnection() throws SQLException {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
	}

	public static DataBaseConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new DataBaseConnection();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("---Connection successful---");
		}

		return conn;
	}

	public void close() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("---Connection closed---");
		}
	}
}