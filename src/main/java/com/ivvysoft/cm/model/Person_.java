package com.ivvysoft.cm.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Person.class)
public class Person_ {

	public static volatile SingularAttribute<Person, Integer> id;
	public static volatile SingularAttribute<Person, String> firstName;
	public static volatile SingularAttribute<Person, String> lastName;
	public static volatile SingularAttribute<Person, String> phone;
	public static volatile SingularAttribute<Person, String> email;
	public static volatile SingularAttribute<Person, User> user;
	
}
