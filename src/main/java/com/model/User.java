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
	private boolean isDeleted = false;
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
	
	public User(long id, String firstName, String lastName, String email, String password,
			LocalDate dateOfBirth, boolean isAdmin) throws UserException {
		this.id = id;
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setDateOfBirth(dateOfBirth);
		this.isAdmin = isAdmin;
		setDeleted(isDeleted);
		this.orders = new ArrayList<Order>();
	}
	
	public User(long id, String firstName, String lastName, String email, String password,
			boolean isDeleted) throws UserException {
		this.id = id;
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setDateOfBirth(dateOfBirth);
		setDeleted(isDeleted);
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((favItems == null) ? 0 : favItems.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + (isMale ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (favItems == null) {
			if (other.favItems != null)
				return false;
		} else if (!favItems.equals(other.favItems))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (isMale != other.isMale)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
	
}
