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

	@Override
	public void create(final User user) throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSession().beginTransaction();
			HibernateSessionFactoryUtil.getSession().save(user);
			HibernateSessionFactoryUtil.getSession().getTransaction().commit();
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	@Override
	public User findByUserName(final String userName) {
		try {
			HibernateSessionFactoryUtil.getSession().beginTransaction();
			
			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSession().getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> root = criteria.from(User.class);

			final ParameterExpression<String> userNameParameter = builder.parameter(String.class);
			final Predicate userNameEquals = builder.equal(root.get(User_.userName), userNameParameter);

			criteria.select(root).where(userNameEquals);
			final User user = HibernateSessionFactoryUtil.getSession().createQuery(criteria)
					.setParameter(userNameParameter, userName).getSingleResult();

			HibernateSessionFactoryUtil.getSession().getTransaction().commit();
			
			return user;
		} catch (NoResultException e) {
			return null;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}
}