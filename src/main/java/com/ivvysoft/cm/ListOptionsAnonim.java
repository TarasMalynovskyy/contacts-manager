package com.ivvysoft.cm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class ListOptionsAnonim {

	void insertNewUser(final User user) throws SQLException {
		final String SQL = "INSERT INTO users (user_name, password) VALUES (?,?)";
		final Connection conn = DataBaseConnection.getInstance().getConnection();
		final PreparedStatement prstmt = conn.prepareStatement(SQL);

		try {
			prstmt.setString(1, user.getUserName());
			prstmt.setString(2, user.getPassword());
			prstmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println();
			System.out.println("User name already exist, please try another!");
			System.out.println();
		}

		conn.close();
		prstmt.close();
	}

	public User getUserByUsername(final String userName) throws SQLException {
		final Connection conn = DataBaseConnection.getInstance().getConnection();
		final String SQL = "SELECT * FROM users WHERE user_name = '" + userName + "'";
		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			final User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));

			return user;
		}

		conn.close();
		stmt.close();
		rs.close();
		return null;

	}

	void exit(final User user) throws SQLException {
		
	}

}
