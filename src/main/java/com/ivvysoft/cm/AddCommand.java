package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

class AddCommand implements Command {

	private final ListOptionsLogined list;

	public AddCommand(final ListOptionsLogined list) {
		this.list = list;
	}

	public boolean execute(final Scanner scan) throws SQLException, ClassNotFoundException {
		System.out.println("Put name of new person:");
		final String name = scan.nextLine();
		System.out.println("Put last name of new person:");
		final String lastName = scan.nextLine();
		System.out.println("Put phone of new person:");
		final String phone = scan.nextLine();
		System.out.println("Put email of new person:");
		final String email = scan.nextLine();

		final Person person = new Person();
		person.setFirstName(name);
		person.setLastName(lastName);
		person.setPhone(phone);
		person.setEmail(email);
		person.setUser_id(IdUserSetter.getUserIdLogined());

		list.add(person);
		
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
