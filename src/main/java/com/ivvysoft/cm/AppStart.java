package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;
import com.ivvysoft.cm.command.Invoker;
import com.ivvysoft.cm.command.anonim.ExitCommand;
import com.ivvysoft.cm.command.anonim.LoginCommand;
import com.ivvysoft.cm.command.anonim.RegistrationCommand;
import com.ivvysoft.cm.command.logedin.CreateCommand;
import com.ivvysoft.cm.command.logedin.DelCommand;
import com.ivvysoft.cm.command.logedin.EditCommand;
import com.ivvysoft.cm.command.logedin.EmailSendingCommand;
import com.ivvysoft.cm.command.logedin.FindCommand;
import com.ivvysoft.cm.command.logedin.LogOutCommand;
import com.ivvysoft.cm.command.logedin.ShowAllCommand;
import com.ivvysoft.cm.repository.DataBaseConnection;

public class AppStart {

	final Scanner scan = new Scanner(System.in);
	final Environment environment = new Environment();
	final Invoker invoker = new Invoker();
	final Invoker invokerAnonim = new Invoker();

	private boolean anonimUser() throws SQLException, ClassNotFoundException {
		System.out.println("Hello, u can choose the option:");
		int n = 1;
		while (n != 0) {
			invokerAnonim.printAvailableCommands();

			try {
				final int commandPositionAnonim = Integer.parseInt(scan.nextLine());
				final boolean permissionCheck = invokerAnonim.execute(commandPositionAnonim, scan);

				if (permissionCheck && environment.getUserId() != 0) {
					loginedUser();
				} else if (permissionCheck && environment.getUserId() == 0) {
					//
				} else {
					DataBaseConnection.getInstance().close();
					n = 0;
				}
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				System.out.println();
				System.out.println("---Choose one option---");
				System.out.println();
			}
		}

		return true;
	}

	private boolean loginedUser() throws SQLException, ClassNotFoundException {
		int n = 1;
		while (n != 0) {
			invoker.printAvailableCommands();

			final int commandPosition = Integer.parseInt(scan.nextLine());
			final boolean permissionCheck = invoker.execute(commandPosition, scan);

			if (permissionCheck) {
				//
			} else {
				DataBaseConnection.getInstance().close();
				n = 0;
			}
		}

		return true;
	}

	public void start() throws ClassNotFoundException, SQLException {
		invokerAnonim.register(new ExitCommand());
		invokerAnonim.register(new RegistrationCommand(environment));
		invokerAnonim.register(new LoginCommand(environment));

		invoker.register(new LogOutCommand());
		invoker.register(new FindCommand(environment));
		invoker.register(new CreateCommand(environment));
		invoker.register(new DelCommand(environment));
		invoker.register(new EditCommand(environment));
		invoker.register(new ShowAllCommand(environment));
		invoker.register(new EmailSendingCommand(environment));

		anonimUser();

		scan.close();
	}
}
