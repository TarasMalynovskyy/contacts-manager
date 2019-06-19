package com.ivvysoft.cm.repository;

import java.sql.SQLException;

import javax.persistence.Query;

import com.ivvysoft.cm.model.User;
import com.ivvysoft.cm.util.HibernateSessionFactoryUtil;

public class UserRepository {

	public void create(final User user) throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			HibernateSessionFactoryUtil.getSessionFactory().save(user);
			HibernateSessionFactoryUtil.getSessionFactory().getTransaction().commit();
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	private final String FIND_BY_USER_NAME = "FROM User AS u WHERE u.userName = :userName";

	public User findByUserName(final String userName) throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			Query query = HibernateSessionFactoryUtil.getSessionFactory().createQuery(FIND_BY_USER_NAME);
			query.setParameter("userName", userName);
			final User user = (User) query.getSingleResult();
			HibernateSessionFactoryUtil.getSessionFactory().getTransaction().commit();

			return user;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}
}