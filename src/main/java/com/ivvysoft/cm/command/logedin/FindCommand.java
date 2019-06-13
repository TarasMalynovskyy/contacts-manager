package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.Scanner;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.Person;

public class FindCommand implements Command {

	private final Environment environment;

	public FindCommand(final Environment environment) {
		this.environment = environment;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		final int loginedUserId = environment.getUserId();
		System.out.println("Put first name or last name of person:\n");
		System.out.print("First Name: ");
		final String firstName = scan.nextLine();
		System.out.println("Last Name: ");
		final String lastName = scan.nextLine();

		final Person person = environment.getPersonRepository().findByFirstOrLastName(loginedUserId, firstName, lastName);
		if (person != null) {
			System.out.println("ID: " + person.getId());
			System.out.println("First Name: " + person.getFirstName());
			System.out.println("Last Name: " + person.getLastName());
			System.out.println("Phone: " + person.getPhone());
			System.out.println("Email: " + person.getEmail());
			System.out.println();
		} else {
			System.out.println("Person not found\n");
		}
		
		return true;
	}

	@Override
	public String getDescription() {
		return "Find person by first or last name";
	}
}
