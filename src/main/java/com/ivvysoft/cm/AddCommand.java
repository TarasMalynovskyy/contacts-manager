package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

class AddCommand implements Command {

	private final PersonRepository list;

	public AddCommand(final PersonRepository list) {
		this.list = list;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		System.out.println("Put name of new person:");
		final String firstName = scan.nextLine();
		System.out.println("Put last name of new person:");
		final String lastName = scan.nextLine();
		System.out.println("Put phone of new person:");
		final String phone = scan.nextLine();
		System.out.println("Put email of new person:");
		final String email = scan.nextLine();
		System.out.println();
		
		final Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhone(phone);
		person.setEmail(email);
		person.setUserId(IdUserSetter.getUserIdLogined());
		
		
		final Person p1 = list.addNew(person);

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

	@Override
	public String toString() {
		return getDescription();
	}

}
