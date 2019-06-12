package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

class ShowAllCommand implements Command {

	private final PersonRepository list;

	public ShowAllCommand(final PersonRepository list) {
		this.list = list;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		final int loginedUserId = IdUserSetter.getUserIdLogined();

		List<Person> listOfPersons = list.showAll(loginedUserId);

		for (Person p : listOfPersons) {
			System.out.println(p);
		}

		System.out.println("You have " + listOfPersons.size() + " persons in your list");
		System.out.println();

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