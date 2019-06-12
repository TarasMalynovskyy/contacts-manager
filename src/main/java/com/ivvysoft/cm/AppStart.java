package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

public class AppStart {

	final Scanner scan = new Scanner(System.in);
	final PersonRepository personsRepository = new PersonRepository();
	final UsersRepository usersRepository = new UsersRepository();
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
				if (permissionCheck && IdUserSetter.getUserIdLogined() != 0) {
					loginedUser();
				} else if (permissionCheck && IdUserSetter.getUserIdLogined() == 0) {
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
		invokerAnonim.register(new RegistrationCommand(usersRepository));
		invokerAnonim.register(new LoginCommand(usersRepository));

		invoker.register(new LogOutCommand());
		invoker.register(new FindCommand(personsRepository));
		invoker.register(new AddCommand(personsRepository));
		invoker.register(new DelCommand(personsRepository));
		invoker.register(new EditCommand(personsRepository));
		invoker.register(new ShowAllCommand(personsRepository));
		invoker.register(new EmailSendingCommand(personsRepository));

		anonimUser();

		scan.close();
	}
}
