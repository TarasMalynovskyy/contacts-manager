package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

public class FindCommand implements Command {

	private final PersonRepository list;

	public FindCommand(final PersonRepository list) {
		this.list = list;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		System.out.println("Put first name or last name of person:");
		System.out.println();
		System.out.print("First Name: ");
		final String firstName = scan.nextLine();
		System.out.println("Last Name: ");
		final String lastName = scan.nextLine();
		final int loginedUserId = IdUserSetter.getUserIdLogined();

		final Person person = list.findByFirstOrLastName(loginedUserId, firstName, lastName);
		if (person == null) {
			System.out.println("Person not found");
			System.out.println();
			return false;
		} else {
			System.out.println("ID: " + person.getId());
			System.out.println("First Name: " + person.getFirstName());
			System.out.println("Last Name: " + person.getLastName());
			System.out.println("Phone: " + person.getPhone());
			System.out.println("Email: " + person.getEmail());
			System.out.println();
			return true;
		}

	}

	@Override
	public String getDescription() {
		return "Find person by first or last name";
	}

	@Override
	public String toString() {
		return getDescription();
	}
}
