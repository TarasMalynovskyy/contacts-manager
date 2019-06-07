package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

public class FindCommand implements Command {

	private final ListOptionsLogined list;

	public FindCommand(final ListOptionsLogined list) {
		this.list = list;
	}

	public boolean execute(final Scanner scan) throws SQLException, ClassNotFoundException {
		System.out.println("Put first name or last name of person:");
		System.out.println();
		System.out.print("Name: ");
		final String name = scan.nextLine();
		System.out.println("Last Name: ");
		final String lastName = scan.nextLine();

		final Person person = new Person();
		person.setFirstName(name);
		person.setLastName(lastName);
		person.setUser_id(IdUserSetter.getUserIdLogined());

		list.find(person);
		return true;
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
