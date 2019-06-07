package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.Scanner;

public class AppStart {

	public void start() throws ClassNotFoundException, SQLException {

		final Scanner scan = new Scanner(System.in);
		final ListOptionsLogined l = new ListOptionsLogined();
		final ListOptionsAnonim lA = new ListOptionsAnonim();
		final Invoker i = new Invoker();
		final Invoker iA = new Invoker();

		iA.register(new ExitCommand(lA));
		iA.register(new RegistrationCommand(lA));
		iA.register(new LoginCommand(lA));
		i.register(new LogOutCommand());
		i.register(new FindCommand(l));
		i.register(new AddCommand(l));
		i.register(new DelCommand(l));
		i.register(new EditCommand(l));
		i.register(new ShowAllCommand(l));
		i.register(new EmailSendingCommand(l));

		System.out.println("Hello, u can choose the option:");

		int n = 1;
		while (n != 0) {
			iA.printAvailableCommands();
			try {
				final int commandPositionAnonim = Integer.parseInt(scan.nextLine());
				if (commandPositionAnonim == 0) {
					DataBaseConnection.getInstance().close();
					n = 0;
				} else if (commandPositionAnonim == 2 && iA.execute(commandPositionAnonim, scan)) {
					while (n != -1) {
						i.printAvailableCommands();
						final int commandPosition = Integer.parseInt(scan.nextLine());
						if (commandPosition == 0) {
							n = -1;
						} else {
							try {
								i.execute(commandPosition, scan);
							} catch (NumberFormatException e) {
								System.out.println("U put unexceptable number!");
							}
						}
					}
				} else if (commandPositionAnonim == 1) {
					iA.execute(commandPositionAnonim, scan);
				}
			} catch (NumberFormatException e) {
				System.out.println("U put unexceptable !");
			} catch (IllegalStateException e) {
				System.out.println("U put unexceptable 2!");
			}
		}
		scan.close();
	}
}
