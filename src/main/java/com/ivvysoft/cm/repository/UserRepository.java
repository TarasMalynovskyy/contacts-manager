package com.ivvysoft.cm.repository;

import java.sql.SQLException;

import com.ivvysoft.cm.model.User;

public interface UserRepository {

	void create(final User user) throws SQLException;

	User findByUserName(final String userName);

}
