package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

class DelCommand implements Command {

	private final ListOptionsLogined list;

	public DelCommand(final ListOptionsLogined list) {
		this.list = list;
	}

	public boolean execute(final Scanner scan) throws SQLException, ClassNotFoundException {
		System.out.println("Put Person ID for DELETE: ");
		final int id = Integer.parseInt(scan.nextLine());
		final Person person = new Person();
		person.setId(id);
		person.setUser_id(IdUserSetter.getUserIdLogined());

		list.del(person);
		return true;
	}

	@Override
	public String getDescription() {
		return "Delete person by ID";
	}

	@Override
	public String toString() {
		return getDescription();
	}
}