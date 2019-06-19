package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.Scanner;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.util.PersonUtils;

public class CreateCommand implements Command {

	private final Environment environment;

	public CreateCommand(final Environment environment) {
		this.environment = environment;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		System.out.println("Put name of new person:");
		final String firstName = scan.nextLine();
		System.out.println("Put last name of new person:");
		final String lastName = scan.nextLine();
		System.out.println("Put phone of new person:");
		final String phone = scan.nextLine();
		System.out.println("Put email of new person:\n");
		final String email = scan.nextLine();
		
		final Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhone(phone);
		person.setEmail(email);
		person.setUser(environment.getUser());
		
		environment.getPersonRepository().create(person);
		
		if (person != null) {
			PersonUtils.personToString(person);
		}
		
		return true;
	}

	@Override
	public String getDescription() {
		return "Add new person";
	}

}
