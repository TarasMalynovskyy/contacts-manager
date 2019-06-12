package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

class DelCommand implements Command {

	private final PersonRepository list;

	public DelCommand(final PersonRepository list) {
		this.list = list;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		final int loginedUserId = IdUserSetter.getUserIdLogined();
		System.out.println("Put Person ID for DELETE: ");
		final int id = Integer.parseInt(scan.nextLine());

		list.delete(loginedUserId, id);
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