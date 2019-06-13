package com.ivvysoft.cm.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Invoker {
	
	private final List<Command> commands = new ArrayList<Command>();

	public void register(final Command command) {
		commands.add(command);
	}

	public boolean execute(final int commandPosition, final Scanner scan) throws SQLException, ClassNotFoundException {
		final Command command = commands.get(commandPosition);
		if (command == null) {
			throw new IllegalStateException("no command registered with position " + commandPosition);
		}

		return command.execute(scan);
	}

	public void printAvailableCommands() {
		for (int index = 0; index < commands.size(); index++) {
			System.out.println(String.format("[%s] - %s", index, commands.get(index).getDescription()));
		}
	}
}