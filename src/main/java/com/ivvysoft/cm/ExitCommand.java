package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

class ExitCommand implements Command {

	public boolean execute(final Scanner scan) throws SQLException {
		return false;
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
