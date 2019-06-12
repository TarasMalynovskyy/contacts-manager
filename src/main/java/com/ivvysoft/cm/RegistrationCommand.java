package com.ivvysoft.cm;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class RegistrationCommand implements Command {

	private final UsersRepository listAnonim;

	public RegistrationCommand(final UsersRepository listAnonim) {
		this.listAnonim = listAnonim;
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
		if (((newUserName == null) || (newUserName.isEmpty())) || ((pass == null) || (pass.isEmpty()))) {
			System.out.println("Password or Login is incorrect");
			execute(scan);
		} else if (pass.equals(pass2)) {
			final User user = new User();
			user.setUserName(newUserName);
			user.setPassword(hashPassword(pass));

			try {
				listAnonim.addNew(user);
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

	@Override
	public String toString() {
		return getDescription();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listAnonim == null) ? 0 : listAnonim.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationCommand other = (RegistrationCommand) obj;
		if (listAnonim == null) {
			if (other.listAnonim != null)
				return false;
		} else if (!listAnonim.equals(other.listAnonim))
			return false;
		return true;
	}

}
