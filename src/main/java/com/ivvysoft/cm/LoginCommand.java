package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class LoginCommand implements Command {

	private final ListOptionsAnonim listAnonim;

	public LoginCommand(final ListOptionsAnonim listAnonim) {
		this.listAnonim = listAnonim;
	}

	// private void checkPass(String plainPassword, String hashedPassword) {
	// if (BCrypt.checkpw(plainPassword, hashedPassword))
	// System.out.println("The password matches.");
	// else
	// System.out.println("The password does not match.");
	// }

	@Override
	public boolean execute(final Scanner scan) throws SQLException, ClassNotFoundException {
		System.out.println("User Name: ");
		final String loginName = scan.nextLine();
		System.out.println("Password: ");
		final String loginPassword = scan.nextLine();

		final User user = listAnonim.getUserByUsername(loginName);

		if (user != null) {
			// checkPass(loginPassword, user.getPassword());
			if (BCrypt.checkpw(loginPassword, user.getPassword())) {
				System.out.println("User and password is correct!");
				IdUserSetter.setUserIdLogined(user.getId());
				return true;
			} else {
				System.out.println("Password is wrong!");
				return false;
			}
		} else {
			System.out.println("Username is wrong!");
			return false;
		}
	}

	@Override
	public String getDescription() {
		return "LogIN";
	}

	@Override
	public String toString() {
		return getDescription();
	}
}
