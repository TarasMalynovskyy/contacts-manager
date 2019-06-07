package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

class EditCommand implements Command {

	private final ListOptionsLogined list;

	public EditCommand(ListOptionsLogined list) {
		this.list = list;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		System.out.println("Put Person ID for Editing:");
		final int id = Integer.parseInt(scan.nextLine());
		System.out.println("Put new first name:");
		final String name = scan.nextLine();
		System.out.println("Put new last name:");
		final String lastName = scan.nextLine();
		System.out.println("Put new phone number:");
		final String phone = scan.nextLine();
		System.out.println("Put new email:");
		final String email = scan.nextLine();

		final Person person = new Person();
		person.setId(id);
		person.setFirstName(name);
		person.setLastName(lastName);
		person.setPhone(phone);
		person.setEmail(email);
		person.setUser_id(IdUserSetter.getUserIdLogined());

		list.edit(person);
		return true;
	}

	@Override
	public String getDescription() {
		return "Edit person";
	}

	@Override
	public String toString() {
		return getDescription();
	}
}