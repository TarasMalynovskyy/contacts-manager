package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.Scanner;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.Person;

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
		person.setUserId(environment.getUserId());
		
		final Person p1 = environment.getPersonRepository().create(person);
		if (p1 != null) {
			System.out.println("ID: " + p1.getId());
			System.out.println("Firs Name: " + p1.getFirstName());
			System.out.println("Last Name: " + p1.getLastName());
			System.out.println("Phone: " + p1.getPhone());
			System.out.println("Email: " + p1.getEmail());
			System.out.println();
		}
		
		return true;
	}

	@Override
	public String getDescription() {
		return "Add new person";
	}

}
