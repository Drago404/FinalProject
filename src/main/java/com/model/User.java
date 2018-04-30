package com.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.UserException;

public class User {
	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isMale;
	private LocalDate dateOfBirth;
	private boolean isAdmin;
	private List<Order> orders;
	private List<Item> favItems;
	
	
	public User(String firstName, String lastName, String email, String password,
			LocalDate dateOfBirth, boolean isAdmin) throws UserException {
		this.id = id;
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setDateOfBirth(dateOfBirth);
		this.isAdmin = isAdmin;
		this.orders = new ArrayList<Order>();
	}
	public User(String firstName, String lastName, String email, String password, LocalDate dateOfBirth) throws UserException {
		this.id = id;
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setDateOfBirth(dateOfBirth);
		this.orders = new ArrayList<Order>();
	}
	
	public User(String firstName, String lastName, String email, String password) throws UserException {
		this.id = id;
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		this.orders = new ArrayList<Order>();
	}
	
	public User() {
		
	}
	
	

	public long getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws UserException {
		if (firstName != null && firstName.trim().length() > 0) {
			this.firstName = firstName;
		} else
			throw new UserException("invalid name");
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws UserException {
		if (lastName != null && lastName.trim().length() > 0) {
			this.lastName = lastName;
		} else
			throw new UserException("invalid name");
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws UserException {
		if (email != null && email.trim().length() > 0) {
			this.email = email;
		} else
			throw new UserException("invalid email");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws UserException {
		if (password != null && password.trim().length() > 0) {
			this.password = password;
		} else
			throw new UserException("invalid password");
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	
}
