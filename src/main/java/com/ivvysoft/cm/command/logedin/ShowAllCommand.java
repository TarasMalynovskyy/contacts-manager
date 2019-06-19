package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.util.PersonUtils;

public class ShowAllCommand implements Command {

	private final Environment environment;

	public ShowAllCommand(final Environment environment) {
		this.environment = environment;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		List<Person> persons = environment.getPersonRepository().showAll(environment.getUser());

		System.out.println(PersonUtils.personsToString(persons));
		System.out.println("You have " + persons.size() + " persons in your list");

		return true;
	}

	@Override
	public String getDescription() {
		return "Show all persons";
	}
}