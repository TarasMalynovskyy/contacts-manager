package com.ivvysoft.cm.repository;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.util.HibernateSessionFactoryUtil;

public class PersonRepository {

	private final String FIND_BY_FIRST_OR_LAST_NAME_HQL = "FROM Person AS p WHERE p.firstName = :firstName  "
			+ "OR p.lastName = :lastName AND p.userId = :userId";

	public Person findByFirstOrLastName(final int userId, final String firstName, final String lastName)
			throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			final Query query = HibernateSessionFactoryUtil.getSessionFactory()
					.createQuery(FIND_BY_FIRST_OR_LAST_NAME_HQL);
			query.setParameter("firstName", firstName);
			query.setParameter("lastName", lastName);
			query.setParameter("userId", userId);
			final Person person = (Person) query.getSingleResult();;
			
			return person;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}
	
	private final String SHOW_ALL_HQL = "FROM Person AS p WHERE p.userId = :userId";

	public List<Person> showAll(final int userId) throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			final Query query = HibernateSessionFactoryUtil.getSessionFactory()
					.createQuery(SHOW_ALL_HQL);
			query.setParameter("userId", userId);
			
			@SuppressWarnings("unchecked")
			final List<Person> persons = query.getResultList();

			return persons;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	public void create(final Person person) throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			HibernateSessionFactoryUtil.getSessionFactory().save(person);
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	private final String DELETE_HQL = "DELETE FROM Person AS p WHERE p.id = :id AND p.userId = :userId";

	public void delete(final int userId, final int id) throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			final Query query = HibernateSessionFactoryUtil.getSessionFactory().createQuery(DELETE_HQL);
			query.setParameter("id", id);
			query.setParameter("userId", userId);
			query.executeUpdate();
			HibernateSessionFactoryUtil.getSessionFactory().getTransaction().commit();
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	public Person edit(final Person person) throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			HibernateSessionFactoryUtil.getSessionFactory().update(person);
			final Person p = HibernateSessionFactoryUtil.getSessionFactory().get(Person.class, person.getId());
			HibernateSessionFactoryUtil.getSessionFactory().getTransaction().commit();

			return p;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	public Person findById(final int id) {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			final Person person = HibernateSessionFactoryUtil.getSessionFactory().get(Person.class, id);

			return person;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	private final String FIND_BY_USER_ID_HQL = "FROM Person AS p WHERE p.id = :id AND p.userId = :userId";

	public Person findByUserId(final int userId, final int id) throws SQLException {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			final Query query = HibernateSessionFactoryUtil.getSessionFactory().createQuery(FIND_BY_USER_ID_HQL);
			query.setParameter("id", id);
			query.setParameter("userId", userId);
			query.executeUpdate();
			final Person person = HibernateSessionFactoryUtil.getSessionFactory().get(Person.class, id);
			return person;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}
}