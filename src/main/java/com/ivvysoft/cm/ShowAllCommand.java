package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

class ShowAllCommand implements Command {

	private final ListOptionsLogined list;

	public ShowAllCommand(final ListOptionsLogined list) {
		this.list = list;
	}

	public boolean execute(final Scanner scan) throws SQLException, ClassNotFoundException {
		final Person person = new Person();

		person.setFirstName("first_name");
		person.setLastName("last_name");
		person.setPhone("phone");
		person.setEmail("email");
		person.setAmountContacts("count");
		person.setUser_id(IdUserSetter.getUserIdLogined());

		list.showAll(person);
		return true;

	}

	@Override
	public String getDescription() {
		return "Show all persons";
	}

	@Override
	public String toString() {
		return getDescription();
	}
}