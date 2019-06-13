package com.ivvysoft.cm.model;

public class Person {

	private int id;
	private int userId;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String amountContacts;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAmountContacts() {
		return amountContacts;
	}

	public void setAmountContacts(String amountContacts) {
		this.amountContacts = amountContacts;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
