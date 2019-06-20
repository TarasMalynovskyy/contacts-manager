package com.ivvysoft.cm.repository;

import java.sql.SQLException;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ivvysoft.cm.model.User;
import com.ivvysoft.cm.model.User_;
import com.ivvysoft.cm.util.HibernateSessionFactoryUtil;

public class UserRepositoryImpl implements UserRepository {

	public void create(final User user) throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			HibernateSessionFactoryUtil.getSessionFactory().save(user);
			HibernateSessionFactoryUtil.getSessionFactory().getTransaction().commit();
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	public User findByUserName(final String userName) {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			
			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSessionFactory().getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> root = criteria.from(User.class);

			final ParameterExpression<String> userNameParameter = builder.parameter(String.class);
			final Predicate userNameEquals = builder.equal(root.get(User_.userName), userNameParameter);

			criteria.select(root).where(userNameEquals);
			final User user = HibernateSessionFactoryUtil.getSessionFactory()
					.createQuery(criteria)
						.setParameter(userNameParameter, userName)
							.getSingleResult();

			HibernateSessionFactoryUtil.getSessionFactory().getTransaction().commit();

			return user;
		} catch (NoResultException e) {
			return null;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}
}