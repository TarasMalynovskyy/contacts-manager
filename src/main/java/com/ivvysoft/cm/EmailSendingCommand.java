package com.ivvysoft.cm;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

public class EmailSendingCommand implements Command {

	private final ListOptionsLogined listOptionsLogined;

	public EmailSendingCommand(ListOptionsLogined list) {
		listOptionsLogined = list;
	}

	@Override
	public boolean execute(final Scanner scan) throws SQLException, ClassNotFoundException {
		System.out.printf("-Enter Person ID you wish to send" + "%n-Enter 000 for sending all avalible persons");
		System.out.println();
		final int id = Integer.parseInt(scan.nextLine());
		System.out.println("Enter email of reciver");
		final String emailAddress = scan.nextLine();

		final Person person = new Person();
		person.setId(id);
		person.setUser_id(IdUserSetter.getUserIdLogined());

		final List<Person> persons = listOptionsLogined.sendContactsToEmail(person);
		Mailer mailer = MailerBuilder
				.withSMTPServer("smtp.gmail.com", 587, "com.ivvysoft.contactsmanager@gmail.com", "Contacts1408988")
				.buildMailer();

		Email email = EmailBuilder.startingBlank().from("	Ivvy Soft", "com.ivvysoft.contactsmanager@gmail.com")
				.to("Taras Malynovskyy", emailAddress).withSubject("Person(s) info avalible for you")
				.withPlainText(persons.toString()).buildEmail();
		if ((id == 000) || (id > 0)) {
			mailer.sendMail(email);
			System.out.println("");
			System.out.println("Email sent successfully");
			return true;
		} else {
			System.out.println("U put unavailable number!");
			return false;
		}
	}

	@Override
	public String getDescription() {
		return "Send person info to email";
	}

	@Override
	public String toString() {
		return getDescription();
	}

}
