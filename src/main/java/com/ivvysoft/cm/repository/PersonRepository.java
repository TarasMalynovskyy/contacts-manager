package com.ivvysoft.cm.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ivvysoft.cm.model.Person;

public class PersonRepository {

	private final String FIND_BY_FIRST_OR_LAST_NAME_SQL = "SELECT * FROM persons AS p WHERE p.first_name = ? OR p.last_name = ? AND p.user_id = ?";
	
	public Person findByFirstOrLastName(
			final int userId, final String firstName, final String lastName) throws SQLException {
		PreparedStatement prstmt = null;
		ResultSet rs = null;

		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_BY_FIRST_OR_LAST_NAME_SQL);
			prstmt.setString(1, firstName);
			prstmt.setString(2, lastName);
			prstmt.setInt(3, userId);
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

	private final String SHOW_ALL_SQL = "SELECT * FROM persons AS p WHERE p.user_id = ?";
	
	public List<Person> showAll(final int userId) throws SQLException {
		PreparedStatement prstmt = null;
		ResultSet rs = null;

		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(SHOW_ALL_SQL);
			prstmt.setInt(1, userId);
			rs = prstmt.executeQuery();

			final List<Person> listOfPersons = new ArrayList<Person>();
			while (rs.next()) {
				listOfPersons.add(extractFromResultSet(rs));
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
	
	private final String CREATE_SQL = "INSERT INTO persons AS p (p.first_name, p.last_name, p.phone, p.email, p.user_id) VALUES (?,?,?,?,?)";
	private final String CREATE_SQL_VIEW = "SELECT * FROM persons AS p WHERE p.id IN (SELECT @@IDENTITY)";

	public Person create(final Person person) throws SQLException {
		PreparedStatement prstmtInsert = null;
		PreparedStatement prstmtSelect = null;
		ResultSet rs = null;

		try {
			prstmtInsert = DataBaseConnection.getInstance().getConnection().prepareStatement(CREATE_SQL);
			prstmtInsert.setString(1, person.getFirstName());
			prstmtInsert.setString(2, person.getLastName());
			prstmtInsert.setString(3, person.getPhone());
			prstmtInsert.setString(4, person.getEmail());
			prstmtInsert.setInt(5, person.getUserId());
			prstmtInsert.executeUpdate();

			prstmtSelect = DataBaseConnection.getInstance().getConnection().prepareStatement(CREATE_SQL_VIEW);
			rs = prstmtSelect.executeQuery();
			
			Person p = null;
			if (rs.next()) {
				p = extractFromResultSet(rs);
			}

			return p;
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
	
	private final String DELETE_SQL = "DELETE FROM persons AS p WHERE p.id = ? AND p.user_id = ?";

	public void delete(final int userId, final int id) throws SQLException {
		PreparedStatement prstmt = null;

		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(DELETE_SQL);
			prstmt.setInt(1, id);
			prstmt.setInt(2, userId);
			prstmt.executeQuery();
		} finally {
			if (prstmt != null) {
				prstmt.close();
			}
		}

	}

	private final String EDIT_SQL = "UPDATE persons AS p SET p.first_name = ?, p.last_name = ?, p.phone = ?, p.email = ? WHERE p.id = ? AND p.user_id = ?";
	private final String EDIT_SQL_VIEW = "SELECT * FROM persons AS p WHERE p.id = ?";

	public Person edit(final Person person) throws SQLException {
		PreparedStatement prstmtUpdate = null;
		PreparedStatement prstmtSelect = null;
		ResultSet rs = null;

		try {
			prstmtUpdate = DataBaseConnection.getInstance().getConnection().prepareStatement(EDIT_SQL);
			prstmtUpdate.setString(1, person.getFirstName());
			prstmtUpdate.setString(2, person.getLastName());
			prstmtUpdate.setString(3, person.getPhone());
			prstmtUpdate.setString(4, person.getEmail());
			prstmtUpdate.setInt(5, person.getId());
			prstmtUpdate.setInt(6, person.getUserId());
			prstmtUpdate.executeUpdate();

			prstmtSelect = DataBaseConnection.getInstance().getConnection().prepareStatement(EDIT_SQL_VIEW);
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
	
	private final String FIND_BY_ID_SQL = "SELECT * FROM persons WHERE user_id = ? AND id = ?";
	
	public Person findById(final int userId, final int id) throws SQLException {
		PreparedStatement prstmt = null;
		ResultSet rs = null;

		try {
			prstmt = DataBaseConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID_SQL);
			prstmt.setInt(1, userId);
			prstmt.setInt(2, id);
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