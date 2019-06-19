package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.Scanner;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.util.PersonUtils;

public class FindCommand implements Command {

	private final Environment environment;

	public FindCommand(final Environment environment) {
		this.environment = environment;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		System.out.println("Put first name or last name of person:\n");
		System.out.print("First Name: ");
		final String firstName = scan.nextLine();
		System.out.println("Last Name: ");
		final String lastName = scan.nextLine();

		final Person person = environment.getPersonRepository().findByFirstOrLastName(environment.getUser(), firstName, lastName);
		
		
		if (person != null) {
			PersonUtils.personToString(person);
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
