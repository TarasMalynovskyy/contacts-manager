package com.ivvysoft.cm.command;

import java.sql.SQLException;
import java.util.Scanner;

public interface Command {
	
	String getDescription();
	
	boolean execute(final Scanner scan) throws SQLException;
	
}