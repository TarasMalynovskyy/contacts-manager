package com.ivvysoft.cm.command.anonim;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.User;

public class RegistrationCommand implements Command {

	private final Environment environment;

	public RegistrationCommand(final Environment environment) {
		this.environment = environment;
	}

	private String hashPassword(String plainTextPassword) {
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
	}

	@Override
	public boolean execute(final Scanner scan) throws SQLException {
		System.out.println("Put new User Name");
		String newUserName = scan.nextLine();
		System.out.println("Put your password");
		String pass = scan.nextLine();
		System.out.println("Put your password again");
		final String pass2 = scan.nextLine();
		
		if (newUserName == null || newUserName.isEmpty() || pass == null || pass.isEmpty()) {
			System.out.println("Password or Login is incorrect");
			execute(scan);
		} else if (pass.equals(pass2)) {
			final User user = new User();
			user.setUserName(newUserName);
			user.setPassword(hashPassword(pass));
			
			try {
				environment.getUserRepository().create(user);
				System.out.println("---New user was created!---");
				
				return true;
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println();
				System.out.println("User name already exist, please try another!");
				System.out.println();
				execute(scan);
			}
		} else {
			System.out.println("The password does not match, try again");
			execute(scan);
		}
		
		return true;
	}

	@Override
	public String getDescription() {
		return "Create new user";
	}
}
