package com.ivvysoft.cm;

public class Person {

	private int id;
	private int user_id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String amountContacts;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	@Override
	public String toString() {
		return "Person ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName
				+ ", Phone: " + phone + ", Email: " + email+"\n";
	}
	
	
}
