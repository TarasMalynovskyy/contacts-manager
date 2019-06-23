package com.ivvysoft.cm.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.model.Person_;
import com.ivvysoft.cm.model.User;
import com.ivvysoft.cm.util.HibernateSessionFactoryUtil;

public class PersonRepositoryImpl implements PersonRepository {

	public Person findByFirstOrLastName(final User user, final String firstName, final String lastName) {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();

			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSessionFactory().getCriteriaBuilder();
			CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
			Root<Person> root = criteria.from(Person.class);

			final ParameterExpression<String> firstNameParameter = builder.parameter(String.class);
			final ParameterExpression<String> lastNameParameter = builder.parameter(String.class);
			final ParameterExpression<User> userIdParameter = builder.parameter(User.class);

			final Predicate firstNameEquals = builder.equal(root.get(Person_.firstName), firstNameParameter);
			final Predicate lastNameEquals = builder.equal(root.get(Person_.lastName), lastNameParameter);
			final Predicate userIdEquals = builder.equal(root.get(Person_.user), userIdParameter);

			criteria.select(root).where(builder.and(builder.or(firstNameEquals, lastNameEquals)), userIdEquals);

			final Person person = (Person) HibernateSessionFactoryUtil.getSessionFactory().createQuery(criteria)
					.setParameter(firstNameParameter, firstName).setParameter(lastNameParameter, lastName)
					.setParameter(userIdParameter, user).getSingleResult();

			return person;
		} catch (NoResultException e) {
			return null;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	public List<Person> showAll(final User user) {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSessionFactory().getCriteriaBuilder();
			CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
			Root<Person> root = criteria.from(Person.class);

			final ParameterExpression<User> userIdParameter = builder.parameter(User.class);
			final Predicate userIdEquals = builder.equal(root.get(Person_.user), userIdParameter);

			criteria.select(root).where(userIdEquals);
			final List<Person> persons = HibernateSessionFactoryUtil.getSessionFactory().createQuery(criteria)
					.setParameter(userIdParameter, user).getResultList();

			return persons;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	public void create(final Person person) {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			HibernateSessionFactoryUtil.getSessionFactory().save(person);
			HibernateSessionFactoryUtil.getSessionFactory().getTransaction().commit();
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	public void delete(final User user, final int id) {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();

			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSessionFactory().getCriteriaBuilder();
			CriteriaDelete<Person> criteriaDelete = builder.createCriteriaDelete(Person.class);
			Root<Person> root = criteriaDelete.from(Person.class);

			final ParameterExpression<User> userIdParameter = builder.parameter(User.class);
			final ParameterExpression<Integer> personIdParameter = builder.parameter(Integer.class);
			final Predicate userIdEquals = builder.equal(root.get(Person_.user), userIdParameter);
			final Predicate personIdEquals = builder.equal(root.get(Person_.id), personIdParameter);
			criteriaDelete.where(builder.and(personIdEquals), userIdEquals);

			HibernateSessionFactoryUtil.getSessionFactory().createQuery(criteriaDelete)
					.setParameter(userIdParameter, user).setParameter(personIdParameter, id).executeUpdate();

			HibernateSessionFactoryUtil.getSessionFactory().getTransaction().commit();
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	public Person edit(final Person person) {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();
			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSessionFactory().getCriteriaBuilder();
			CriteriaUpdate<Person> criteriaUpdate = builder.createCriteriaUpdate(Person.class);
			Root<Person> root = criteriaUpdate.from(Person.class);

			final ParameterExpression<Person> personParameters = builder.parameter(Person.class);
			final Predicate personEquals = builder.equal(root.as(Person_.class), personParameters);
			criteriaUpdate.where(personEquals);

			final Person p = (Person) HibernateSessionFactoryUtil.getSessionFactory().createQuery(criteriaUpdate)
					.setParameter(personParameters, person).getSingleResult();
			
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

	public Person findByUserId(final User user, final int id) {
		try {
			HibernateSessionFactoryUtil.getSessionFactory().beginTransaction();

			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSessionFactory().getCriteriaBuilder();
			CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
			Root<Person> root = criteria.from(Person.class);

			final ParameterExpression<Integer> personIdParameter = builder.parameter(Integer.class);
			final ParameterExpression<User> userIdParameter = builder.parameter(User.class);

			final Predicate personIdEquals = builder.equal(root.get(Person_.id), personIdParameter);
			final Predicate userIdEquals = builder.equal(root.get(Person_.user), userIdParameter);

			criteria.select(root).where(builder.and(personIdEquals), (userIdEquals));

			final Person person = HibernateSessionFactoryUtil.getSessionFactory().createQuery(criteria)
					.setParameter(personIdParameter, id).setParameter(userIdParameter, user).getSingleResult();

			return person;
		} catch (NoResultException e) {
			return null;
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}
}