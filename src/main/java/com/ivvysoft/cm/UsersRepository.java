package com.ivvysoft.cm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {

	public void addNew(final User user) throws SQLException {
		final String SQL = "INSERT INTO users (user_name, password) VALUES (?,?)";
		PreparedStatement prstmt = null;
		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);
			prstmt.setString(1, user.getUserName());
			prstmt.setString(2, user.getPassword());
			prstmt.executeUpdate();

		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
		}
	}

	public User getUserByUsername(final String userName) throws SQLException {
		final String SQL = "SELECT * FROM users WHERE user_name = ?";
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);
			prstmt.setString(1, userName);
			rs = prstmt.executeQuery();

			User user = null;
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
			}
			return user;
		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

}
