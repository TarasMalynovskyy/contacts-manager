package com.ivvysoft.cm.model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel (User.class)
public class User_ {
	
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Person> persons;

}
