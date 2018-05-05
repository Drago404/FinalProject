package com.model;

import com.exceptions.CategoryException;

public class Category {
	
	private int id;
	private String name;
	
	
	public Category(String name) {
		this.id = id;
		setName(name);
	}	
	
	public Category() {

	}	


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		if (name != null && name.trim().length() > 0) {
			this.name = name;
		} else
			new CategoryException("invalid category name");

	}	
	
}
