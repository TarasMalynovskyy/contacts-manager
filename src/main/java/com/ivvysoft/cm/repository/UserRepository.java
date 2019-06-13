package com.ivvysoft.cm.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ivvysoft.cm.model.User;

public class UserRepository {
	
	private final String CREATE_SQL = "INSERT INTO users (user_name, password) VALUES (?,?)";
	
	public void create(final User user) throws SQLException {
		PreparedStatement prstmt = null;
		
		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(CREATE_SQL);
			prstmt.setString(1, user.getUserName());
			prstmt.setString(2, user.getPassword());
			prstmt.executeUpdate();
		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
		}
	}

	private final String FIND_USER_BY_USERNAME_SQL = "SELECT * FROM users WHERE user_name = ?";
	
	public User findUserByUsername(final String userName) throws SQLException {
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		
		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_USER_BY_USERNAME_SQL);
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
