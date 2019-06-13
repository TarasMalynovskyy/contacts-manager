package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.Scanner;

import com.ivvysoft.cm.command.Command;

public class LogOutCommand implements Command{

	@Override
	public boolean execute(Scanner scan) throws SQLException {
		return false;
	}

	@Override
	public String getDescription() {
		return "Log out";
	}
}
