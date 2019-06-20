package com.ivvysoft.cm;

import com.ivvysoft.cm.model.User;
import com.ivvysoft.cm.repository.PersonRepositoryImpl;
import com.ivvysoft.cm.repository.UserRepositoryImpl;

public class Environment {
	
	private PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
	private UserRepositoryImpl userRepository = new UserRepositoryImpl();
	private User user = null;
//	private int userId;
	

	public PersonRepositoryImpl getPersonRepository() {
		return personRepository;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPersonRepository(PersonRepositoryImpl personRepository) {
		this.personRepository = personRepository;
	}

	public UserRepositoryImpl getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepositoryImpl userRepository) {
		this.userRepository = userRepository;
	}

//	public int getUserId() {
//		return userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}

}
