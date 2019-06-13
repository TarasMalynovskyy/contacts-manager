package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.Scanner;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.Person;

public class EditCommand implements Command {

	private final Environment environment;

	public EditCommand(final Environment environment) {
		this.environment = environment;
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
		person.setUserId(environment.getUserId());

		final Person person2 = environment.getPersonRepository().edit(person);
		if (person2 != null) {
			System.out.println("New ID: " + person2.getId());
			System.out.println("New First Name: " + person2.getFirstName());
			System.out.println("New Last Name: " + person2.getLastName());
			System.out.println("New Phone: " + person2.getPhone());
			System.out.println("New Email: " + person2.getEmail());
			System.out.println();
		}
		
		return true;
	}

	@Override
	public String getDescription() {
		return "Edit person";
	}
}