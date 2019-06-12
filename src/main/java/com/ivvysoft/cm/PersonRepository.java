package com.ivvysoft.cm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

	public Person findByFirstOrLastName(final int userId, final String firstName, final String lastName)
			throws SQLException {
		final String SQL = "SELECT * FROM persons WHERE first_name = ? OR last_name = ? AND user_id = ?";
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);
			prstmt.setString(1, firstName);
			prstmt.setString(2, lastName);
			prstmt.setInt(3, userId);
			rs = prstmt.executeQuery(SQL);

			Person person = null;
			while (rs.next()) {
				person = new Person();
				getResultSet(person, rs);
			}
			return person;
		} finally {
			if (prstmt != null) {
				prstmt.close();
				rs.close();
			}
		}
	}

	public List<Person> showAll(final int userId) throws SQLException {
		final String SQL = "SELECT * FROM persons WHERE user_id = " + userId;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DataBaseConnection.getInstance().getConnection().createStatement();
			rs = stmt.executeQuery(SQL);
			List<Person> listOfPersons = new ArrayList<Person>();
			Person person = null;
			while (rs.next()) {
				person = new Person();
				getResultSet(person, rs);
				listOfPersons.add(person);
			}

			return listOfPersons;
		} finally {
			if (stmt != null) {
				stmt.close();
				rs.close();
			}
		}
	}

	public Person addNew(final Person person) throws SQLException {
		String SQL = "INSERT INTO persons (first_name, last_name, phone, email, user_id) VALUES (?,?,?,?,?)";
		String sqlView = "SELECT * FROM persons WHERE id IN (SELECT @@IDENTITY)";
		PreparedStatement prstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);
			prstmt.setString(1, person.getFirstName());
			prstmt.setString(2, person.getLastName());
			prstmt.setString(3, person.getPhone());
			prstmt.setString(4, person.getEmail());
			prstmt.setInt(5, person.getUserId());
			prstmt.executeUpdate();
			stmt = DataBaseConnection.getInstance().getConnection().createStatement();
			rs = stmt.executeQuery(sqlView);

			while (rs.next()) {
				getResultSet(person, rs);
			}

			return person;
		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
			if (stmt != null) {
				stmt.close();
				rs.close();
			}
		}
	}

	public void delete(final int userId, final int id) throws SQLException {
		String SQL = "DELETE FROM persons WHERE id = " + id + " AND user_id = " + userId;
		Statement stmt = null;
		try {
			stmt = DataBaseConnection.getInstance().getConnection().createStatement();
			stmt.executeQuery(SQL);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

	}

	public Person edit(final Person person) throws SQLException {
		final String SQL = "UPDATE persons SET first_name =?, last_name =?, phone =?, email =? WHERE id =? AND user_id =?";
		final String sqlView = "SELECT * FROM persons WHERE id =\"" + person.getId() + "\"";

		Statement stmt = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			stmt = DataBaseConnection.getInstance().getConnection().createStatement();
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);

			prstmt.setString(1, person.getFirstName());
			prstmt.setString(2, person.getLastName());
			prstmt.setString(3, person.getPhone());
			prstmt.setString(4, person.getEmail());
			prstmt.setInt(5, person.getId());
			prstmt.setInt(6, person.getUserId());
			prstmt.executeUpdate();

			rs = stmt.executeQuery(sqlView);

			if (rs.next()) {
				getResultSet(person, rs);
			}

			return person;
		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
			if (stmt != null) {
				stmt.close();
				rs.close();
			}

		}
	}

	public List<Person> selectAllByUserAndPersonId(final int userId, final int id) throws SQLException {
		final String SQL = "SELECT * FROM persons WHERE user_id = " + userId + " AND id = " + id;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DataBaseConnection.getInstance().getConnection().createStatement();
			rs = stmt.executeQuery(SQL);
			List<Person> pal = null;
			while (rs.next()) {
				pal = new ArrayList<Person>();
				Person person = new Person();
				getResultSet(person, rs);
				pal.add(person);
			}

			return pal;
		} finally {
			if (stmt != null) {
				stmt.close();
				rs.close();
			}
		}
	}

	private void getResultSet(final Person person, ResultSet rs) throws SQLException {
		person.setId(rs.getInt("id"));
		person.setFirstName(rs.getString("first_name"));
		person.setLastName(rs.getString("last_name"));
		person.setPhone(rs.getString("phone"));
		person.setEmail(rs.getString("email"));
	}
}