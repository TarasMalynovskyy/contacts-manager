package com.ivvysoft.cm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListOptionsLogined {

	Statement stmt;
	ResultSet rs;
	PreparedStatement prstmt = null;
	Connection conn = null;

	void find(final Person person) throws SQLException {
		final String SQL = "SELECT * FROM persons WHERE first_name ='" + person.getFirstName() + "'OR last_name ='"
				+ person.getLastName() + "' AND user_id = " + person.getUser_id();

		stmt = DataBaseConnection.getInstance().getConnection().createStatement();
		rs = stmt.executeQuery(SQL);
		if (!rs.next()) {
			System.out.println("---User does not exist---");
		}

		while (rs.next()) {
			person.setId(rs.getInt("id"));
			person.setFirstName(rs.getString("first_name"));
			person.setLastName(rs.getString("last_name"));
			person.setPhone(rs.getString("phone"));
			person.setEmail(rs.getString("email"));

			System.out.println("ID: " + person.getId());
			System.out.println("First Name: " + person.getFirstName());
			System.out.println("Last Name: " + person.getLastName());
			System.out.println("Phone: " + person.getPhone());
			System.out.println("Email: " + person.getEmail());
			System.out.println();
		}
		stmt.close();
		rs.close();
	}

	void showAll(final Person person) throws SQLException {
		String SQL = "SELECT * FROM persons WHERE user_id = " + person.getUser_id();

		stmt = DataBaseConnection.getInstance().getConnection().createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			person.setId(rs.getInt("id"));
			person.setFirstName(rs.getString("first_name"));
			person.setLastName(rs.getString("last_name"));
			person.setPhone(rs.getString("phone"));
			person.setEmail(rs.getString("email"));

			System.out.println("ID: " + person.getId());
			System.out.println("First Name: " + person.getFirstName());
			System.out.println("Last Name: " + person.getLastName());
			System.out.println("Phone: " + person.getPhone());
			System.out.println("Email: " + person.getEmail());
			System.out.println();
		}

		String sqlView = "SELECT COUNT(*) AS count FROM persons WHERE user_id =" + person.getUser_id();

		stmt = DataBaseConnection.getInstance().getConnection().createStatement();
		rs = stmt.executeQuery(sqlView);

		while (rs.next()) {
			person.setAmountContacts(rs.getString("count"));

			System.out.println("Amount or all your contacts: " + person.getAmountContacts());
			System.out.println();
		}
		stmt.close();
		rs.close();
	}

	void add(final Person person) throws SQLException {
		String SQL = "INSERT INTO persons (first_name, last_name, phone, email, user_id) VALUES (\""
				+ person.getFirstName() + "\", \"" + person.getLastName() + "\", \"" + person.getPhone() + "\", \""
				+ person.getEmail() + "\", " + person.getUser_id() + ")";

		stmt = DataBaseConnection.getInstance().getConnection().createStatement();
		rs = stmt.executeQuery(SQL);

		String sqlView = "SELECT * FROM persons ORDER BY id DESC LIMIT 1";

		stmt = DataBaseConnection.getInstance().getConnection().createStatement();
		rs = stmt.executeQuery(sqlView);

		while (rs.next()) {
			person.setId(rs.getInt("id"));
			person.setFirstName(rs.getString("first_name"));
			person.setLastName(rs.getString("last_name"));
			person.setPhone(rs.getString("phone"));
			person.setEmail(rs.getString("email"));

			System.out.println("ID: " + person.getId());
			System.out.println("First Name: " + person.getFirstName());
			System.out.println("Last Name: " + person.getLastName());
			System.out.println("Phone: " + person.getPhone());
			System.out.println("Email: " + person.getEmail());
			System.out.println();
		}
		stmt.close();
		rs.close();
	}

	void del(final Person person) throws SQLException {
		String SQL = "DELETE FROM persons WHERE id = " + person.getId() + " AND user_id = " + person.getUser_id();

		stmt = DataBaseConnection.getInstance().getConnection().createStatement();
		rs = stmt.executeQuery(SQL);
		if (!rs.next()) {
			System.out.println("---ID is unknown!---");
		}
		stmt.close();
		rs.close();
	}

	void edit(final Person person) throws SQLException {
		final String SQL = "UPDATE persons SET first_name =\"" + person.getFirstName() + "\", last_name =\""
				+ person.getLastName() + "\", phone =\"" + person.getPhone() + "\", email =\"" + person.getEmail()
				+ "\" WHERE id =\"" + person.getId() + "\"AND user_id = " + person.getUser_id();

		stmt = DataBaseConnection.getInstance().getConnection().createStatement();
		rs = stmt.executeQuery(SQL);
		if (!rs.next()) {
			System.out.println("---ID is unknown!---");
		}

		final String sqlView = "SELECT * FROM persons WHERE id =\"" + person.getId() + "\"";

		stmt = DataBaseConnection.getInstance().getConnection().createStatement();
		rs = stmt.executeQuery(sqlView);

		while (rs.next()) {
			person.setId(rs.getInt("id"));
			person.setFirstName(rs.getString("first_name"));
			person.setLastName(rs.getString("last_name"));
			person.setPhone(rs.getString("phone"));
			person.setEmail(rs.getString("email"));

			System.out.println("New ID: " + person.getId());
			System.out.println("New First Name: " + person.getFirstName());
			System.out.println("New Last Name: " + person.getLastName());
			System.out.println("New Phone: " + person.getPhone());
			System.out.println("New Email: " + person.getEmail());
			System.out.println();
		}
		stmt.close();
		rs.close();
	}

	void logOut() throws SQLException {
		DataBaseConnection.getInstance().getConnection().close();
	}

	public List<Person> sendContactsToEmail(final Person person) throws SQLException {
		String SQL = "";
		if (person.getId() == 000) {
			SQL = "SELECT * FROM persons WHERE user_id = " + person.getUser_id();
		} else {
			SQL = "SELECT * FROM persons WHERE user_id = " + person.getUser_id() + " AND id = " + person.getId();
		}

		stmt = DataBaseConnection.getInstance().getConnection().createStatement();
		rs = stmt.executeQuery(SQL);
		final List<Person> pal = new ArrayList<Person>();
		while (rs.next()) {
			final Person person1 = new Person();

			person1.setId(rs.getInt("id"));
			person1.setFirstName(rs.getString("first_name"));
			person1.setLastName(rs.getString("last_name"));
			person1.setPhone(rs.getString("phone"));
			person1.setEmail(rs.getString("email"));

			pal.add(person1);
		}
		stmt.close();
		rs.close();
		return pal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rs == null) ? 0 : rs.hashCode());
		return result;
	}

}