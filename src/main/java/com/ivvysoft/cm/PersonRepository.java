package com.ivvysoft.cm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			prstmt.executeUpdate();

			rs = prstmt.executeQuery();

			Person person = null;
			while (rs.next()) {
				person = extractFromResultSet(rs);
			}
			return person;
		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

	public List<Person> showAll(final int userId) throws SQLException {
		final String SQL = "SELECT * FROM persons WHERE user_id = ?";
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);
			prstmt.setInt(1, userId);
			prstmt.executeUpdate();

			rs = prstmt.executeQuery();
			List<Person> listOfPersons = new ArrayList<Person>();
			Person person = null;
			while (rs.next()) {
				person = extractFromResultSet(rs);
				listOfPersons.add(person);
			}

			return listOfPersons;
		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

	public Person addNew(final Person person) throws SQLException {
		String SQL = "INSERT INTO persons (first_name, last_name, phone, email, user_id) VALUES (?,?,?,?,?)";
		String sqlView = "SELECT * FROM persons WHERE id IN (SELECT @@IDENTITY)";
		PreparedStatement prstmtInsert = null;
		PreparedStatement prstmtSelect = null;
		ResultSet rs = null;
		try {
			prstmtInsert = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);
			prstmtSelect = DataBaseConnection.getInstance().getConnection().prepareStatement(sqlView);

			prstmtInsert.setString(1, person.getFirstName());
			prstmtInsert.setString(2, person.getLastName());
			prstmtInsert.setString(3, person.getPhone());
			prstmtInsert.setString(4, person.getEmail());
			prstmtInsert.setInt(5, person.getUserId());
			prstmtInsert.executeUpdate();

			rs = prstmtSelect.executeQuery();
			Person p1 = null;
			if (rs.next()) {
				p1 = extractFromResultSet(rs);
			}

			return p1;
		} finally {
			if (prstmtInsert != null) {
				prstmtInsert.close();
			}
			if (prstmtSelect != null) {
				prstmtSelect.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

	public void delete(final int userId, final int id) throws SQLException {
		String SQL = "DELETE FROM persons WHERE id = ? AND user_id = ?";
		PreparedStatement prstmt = null;
		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);
			prstmt.setInt(1, id);
			prstmt.setInt(2, userId);
			prstmt.executeQuery();
		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
		}

	}

	public Person edit(final Person person) throws SQLException {
		final String SQL = "UPDATE persons SET first_name = ?, last_name = ?, phone = ?, email = ? WHERE id = ? AND user_id = ?";
		final String sqlView = "SELECT * FROM persons WHERE id = ?";

		PreparedStatement prstmtUpdate = null;
		PreparedStatement prstmtSelect = null;
		ResultSet rs = null;
		try {
			prstmtUpdate = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);
			prstmtSelect = DataBaseConnection.getInstance().getConnection().prepareStatement(sqlView);

			prstmtUpdate.setString(1, person.getFirstName());
			prstmtUpdate.setString(2, person.getLastName());
			prstmtUpdate.setString(3, person.getPhone());
			prstmtUpdate.setString(4, person.getEmail());
			prstmtUpdate.setInt(5, person.getId());
			prstmtUpdate.setInt(6, person.getUserId());
			prstmtUpdate.executeUpdate();

			prstmtSelect.setInt(1, person.getId());
			rs = prstmtSelect.executeQuery();
			Person p1 = null;
			if (rs.next()) {
				p1 = extractFromResultSet(rs);
			}

			return p1;
		} finally {
			if (prstmtUpdate != null) {
				prstmtUpdate.close();
			}
			if (prstmtSelect != null) {
				prstmtSelect.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

	public List<Person> selectAllByUserAndPersonId(final int userId, final int id) throws SQLException {
		final String SQL = "SELECT * FROM persons WHERE user_id = ? AND id = ?";
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(SQL);
			prstmt.setInt(1, userId);
			prstmt.setInt(2, id);

			rs = prstmt.executeQuery();
			List<Person> pal = new ArrayList<Person>();
			Person person = null;
			while (rs.next()) {
				person = extractFromResultSet(rs);
				pal.add(person);
			}

			return pal;

		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}

	private Person extractFromResultSet(final ResultSet rs) throws SQLException {
		final Person person = new Person();
		person.setId(rs.getInt("id"));
		person.setFirstName(rs.getString("first_name"));
		person.setLastName(rs.getString("last_name"));
		person.setPhone(rs.getString("phone"));
		person.setEmail(rs.getString("email"));

		return person;
	}
}