package com.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Order {

	private int id;
	private Map items;
	private String ordererFirstName;
	private String ordererLastName;
	private String ordererCity;
	private String ordererPhone;
	private String ordererPostCode;
	private String ordererStreet;
	private int ordererStreetNumber;
	private String ordererBlock;
	private String ordererEnterance;
	private int ordererFloor;
	private String ordererAppartment;
	private String description;
	private int userId;

	public Order(int id, String ordererFirstName, String ordererLastName, String ordererCity, String ordererPhone,
			String ordererPostCode, String ordererStreet, int userId) {
		this.id = id;
		this.ordererFirstName = ordererFirstName;
		this.ordererLastName = ordererLastName;
		this.ordererCity = ordererCity;
		this.ordererPhone = ordererPhone;
		this.ordererPostCode = ordererPostCode;
		this.ordererStreet = ordererStreet;
		this.userId = userId;
		this.items = new HashMap<Integer, Item>();
	}

	public int getId() {
		return id;
	}

	public Map getItems() {
		return Collections.unmodifiableMap(items);
	}

	

}
