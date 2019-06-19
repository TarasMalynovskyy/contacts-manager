package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.Scanner;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;

public class DelCommand implements Command {

	private final Environment environment;

	public DelCommand(final Environment environment) {
		this.environment = environment;
	}

	public boolean execute(final Scanner scan) throws SQLException {
		System.out.println("Put Person ID for DELETE: ");
		final int id = Integer.parseInt(scan.nextLine());
		environment.getPersonRepository().delete(environment.getUser(), id);
		
		return true;
	}

	@Override
	public String getDescription() {
		return "Delete person by ID";
	}
}