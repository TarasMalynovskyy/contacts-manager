package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.Scanner;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.util.PersonUtils;

public class EditCommand implements Command {

	private final Environment environment;

	public EditCommand(final Environment environment) {
		this.environment = environment;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		System.out.println("Put Person ID for Editing:");
		final int id = Integer.parseInt(scan.nextLine());
		Person person = environment.getPersonRepository().findById(id);

		System.out.println("Put new first name:");
		final String firstName = scan.nextLine();
		System.out.println("Put new last name:");
		final String lastName = scan.nextLine();
		System.out.println("Put new phone number:");
		final String phone = scan.nextLine();
		System.out.println("Put new email:");
		final String email = scan.nextLine();

		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhone(phone);
		person.setEmail(email);

		final Person p = environment.getPersonRepository().edit(person);
		if (p != null) {
			PersonUtils.personToString(p);
		}
		
		return true;
	}

	@Override
	public String getDescription() {
		return "Edit person";
	}
}