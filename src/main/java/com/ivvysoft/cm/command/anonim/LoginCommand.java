package com.ivvysoft.cm.command.anonim;

import java.sql.SQLException;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.User;

public class LoginCommand implements Command {

	private final Environment environment;

	public LoginCommand(final Environment environment) {
		this.environment = environment;
	}

	@Override
	public boolean execute(final Scanner scan) throws SQLException {
		System.out.println("User Name: ");
		final String loginName = scan.nextLine();
		System.out.println("Password: ");
		final String loginPassword = scan.nextLine();
		
		final User user = environment.getUserRepository().findByUserName(loginName);
		if (user != null) {
			if (BCrypt.checkpw(loginPassword, user.getPassword())) {
				System.out.println("User and password is correct!");
				environment.setUserId(user.getId());

				return true;
			} else {
				System.out.println("Password is wrong!");
				execute(scan);
			}
		} else {
			System.out.println("Username is wrong!");
			execute(scan);
		}

		return true;
	}

	@Override
	public String getDescription() {
		return "LogIN";
	}
}
