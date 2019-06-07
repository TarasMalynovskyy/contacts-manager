package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

public class LogOutCommand implements Command{

	@Override
	public boolean execute(Scanner scan) throws SQLException, ClassNotFoundException {
		return true;
	}

	@Override
	public String getDescription() {
		return "Log out";
	}

	@Override
	public String toString() {
		return getDescription();
	}
	
	

}
