package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

class ExitCommand implements Command {

	private final ListOptionsAnonim listAnonim;

	public ExitCommand(final ListOptionsAnonim listAnonim) {
		this.listAnonim = listAnonim;
	}

	public boolean execute(final Scanner scan) throws SQLException, ClassNotFoundException {
		final User user = new User();
		listAnonim.exit(user);
		return true;
	}

	@Override
	public String getDescription() {
		return "Exit";
	}

	@Override
	public String toString() {
		return getDescription();
	}
}
