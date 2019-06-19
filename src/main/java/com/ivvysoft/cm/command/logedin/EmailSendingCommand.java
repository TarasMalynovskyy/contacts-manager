package com.ivvysoft.cm.command.logedin;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

import com.ivvysoft.cm.Environment;
import com.ivvysoft.cm.command.Command;
import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.util.PersonUtils;

public class EmailSendingCommand implements Command {

	private final Environment environment;

	public EmailSendingCommand(final Environment environment) {
		this.environment = environment;
	}

	@Override
	public boolean execute(final Scanner scan) throws SQLException {
		System.out.printf("-Enter Person ID you wish to send" + "\n-Enter 000 for sending all avalible persons\n");
		final int id = Integer.parseInt(scan.nextLine());
		System.out.println("Enter email of reciver");
		final String inputEmail = scan.nextLine();

		final String emailValidationPattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
				+ "*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

		Pattern r = Pattern.compile(emailValidationPattern);
		Matcher m = r.matcher(inputEmail);

		if (m.matches()) {
			List<Person> persons = null;
			Person p = null;

			if (id == 000) {
				persons = environment.getPersonRepository().showAll(environment.getUser());
			} else {
				p = environment.getPersonRepository().findByUserId(environment.getUser(), id);
			}

			Mailer mailer = MailerBuilder
					.withSMTPServer("smtp.gmail.com", 587, "com.ivvysoft.contactsmanager@gmail.com", "Contacts1408988")
					.buildMailer();

			Email email = EmailBuilder.startingBlank().from("Ivvy Soft", "com.ivvysoft.contactsmanager@gmail.com")
					.to(inputEmail).withSubject("Person(s) info avalible for you")
					.withPlainText(PersonUtils.personsToString(persons)).buildEmail();

			if (!persons.isEmpty() || p != null) {
				mailer.sendMail(email);
				System.out.println("\n---Email sent successfully---\n");
			} else {
				System.out.println("\n---U put unavailable number!---\n");
				execute(scan);
			}
		} else {
			System.out.println("\n---Email address has incorrect format---\n");
		}

		return true;
	}

	@Override
	public String getDescription() {
		return "Send person info to email";
	}
}
