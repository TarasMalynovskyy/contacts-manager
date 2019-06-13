package com.ivvysoft.cm.util;

import java.util.List;

import com.ivvysoft.cm.model.Person;

public class PersonUtils {
	
	public static String personsToString(final List<Person> persons) {
		String resultString = "";
		for (int i = 0; i < persons.size(); i++) {
			int id = persons.get(i).getId();
			String firstName = persons.get(i).getFirstName();
			String lastName = persons.get(i).getLastName();
			String phone = persons.get(i).getPhone();
			String email = persons.get(i).getEmail();
			
			resultString += "ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nPhone: " + phone
					+ "\nEmail: " + email+"\n\n";
		}
		
		return resultString;
	}
}
