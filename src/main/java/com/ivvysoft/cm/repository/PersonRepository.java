package com.ivvysoft.cm.repository;

import java.util.List;

import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.model.User;

public interface PersonRepository {

	Person findByFirstOrLastName(final User user, final String firstName, final String lastName);

	List<Person> showAll(final User user);

	void create(final Person person);

	void delete(final User user, final int id);

	Person edit(final Person person);

	Person findById(final int id);

	Person findByUserId(final User user, final int id);

}
