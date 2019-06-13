package com.ivvysoft.cm.command.anonim;

import java.sql.SQLException;
import java.util.Scanner;

import com.ivvysoft.cm.command.Command;

public class ExitCommand implements Command {

	public boolean execute(final Scanner scan) throws SQLException {
		return false;
	}

	@Override
	public String getDescription() {
		return "Exit";
	}
}
