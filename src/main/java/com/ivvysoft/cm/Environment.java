package com.ivvysoft.cm;

import com.ivvysoft.cm.repository.PersonRepository;
import com.ivvysoft.cm.repository.UserRepository;

public class Environment {
	
	private PersonRepository personRepository = new PersonRepository();
	private UserRepository userRepository = new UserRepository();
	private int userId;

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
