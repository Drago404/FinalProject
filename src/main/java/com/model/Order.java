package com.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.exceptions.ItemException;
import com.exceptions.OrderException;

public class Order {

	private long id;
	private Map<Long, Item> items;
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

	public Order(String ordererFirstName, String ordererLastName, String ordererCity, String ordererPhone,
			String ordererPostCode, String ordererStreet) throws OrderException {
		this.id = id;
		setOrdererFirstName(ordererFirstName);
		setOrdererLastName(ordererLastName);
		setOrdererCity(ordererCity);
		setOrdererPhone(ordererPhone);
		setOrdererPostCode(ordererPostCode);
		setOrdererStreet(ordererStreet);
		this.userId = userId;
		this.items = new HashMap<Long, Item>();
	}
	
	public Order(String ordererFirstName, String ordererLastName, String ordererCity, String ordererPhone,
			String ordererPostCode, String ordererStreet, int ordererStreetNumber, String ordererBlock,
			String ordererEnterance, int ordererFloor, String ordererAppartment, String description) throws OrderException {
		this.id = id;
		setOrdererFirstName(ordererFirstName);
		setOrdererLastName(ordererLastName);
		setOrdererCity(ordererCity);
		setOrdererPhone(ordererPhone);
		setOrdererPostCode(ordererPostCode);
		setOrdererStreet(ordererStreet);
		setOrdererStreetNumber(ordererStreetNumber);
		setOrdererBlock(ordererBlock);
		setOrdererEnterance(ordererEnterance);
		setOrdererFloor(ordererFloor);
		setOrdererAppartment(ordererAppartment);
		setDescription(description);
		this.userId = userId;
		this.items = new HashMap<Long, Item>();
	}
	
	public Order() {
		
	}

	public long getId() {
		return id;
	}

	public Map<Long, Item> getItems() {
		return Collections.unmodifiableMap(items);
	}

	public String getOrdererFirstName() {
		return ordererFirstName;
	}

	public void setOrdererFirstName(String ordererFirstName) throws OrderException {
		if (ordererFirstName != null && ordererFirstName.trim().length() > 0) {
			this.ordererFirstName = ordererFirstName;
		} else
			throw new OrderException("invalid orderer first name ");
	
	}

	public String getOrdererLastName() {
		return ordererLastName;
	}

	public void setOrdererLastName(String ordererLastName) throws OrderException {
		if (ordererLastName != null && ordererLastName.trim().length() > 0) {
			this.ordererLastName = ordererLastName;
		} else
			throw new OrderException("invalid orderer last name ");
	
	}

	public String getOrdererCity() {
		return ordererCity;
	}

	public void setOrdererCity(String ordererCity) throws OrderException {
		if (ordererCity != null && ordererCity.trim().length() > 0) {
			this.ordererCity = ordererCity;
		} else
			throw new OrderException("invalid orderer city ");
	}
	public String getOrdererPhone() {
		return ordererPhone;
	}

	public void setOrdererPhone(String ordererPhone) throws OrderException {
		if (ordererPhone != null && ordererPhone.trim().length() > 0 && ordererPhone.length() == 10) {
			this.ordererPhone = ordererPhone;
		} else
			throw new OrderException("invalid orderer phone ");
	}
	public String getOrdererPostCode() {
		return ordererPostCode;
	}

	public void setOrdererPostCode(String ordererPostCode) throws OrderException {
		if (ordererPostCode != null && ordererPostCode.trim().length() > 0 && ordererPostCode.length() == 4) {
			this.ordererPostCode = ordererPostCode;
		} else
			throw new OrderException("invalid orderer postcode ");
	}
	public String getOrdererStreet() {
		return ordererStreet;
	}

	public void setOrdererStreet(String ordererStreet) throws OrderException {
		if (ordererStreet != null && ordererStreet.trim().length() > 0) {
			this.ordererStreet = ordererStreet;
		} else
			throw new OrderException("invalid orderer street ");
	}

	public int getOrdererStreetNumber() {
		return ordererStreetNumber;
	}

	public void setOrdererStreetNumber(int ordererStreetNumber) throws OrderException {
		if(ordererStreetNumber > 0 ) {
		this.ordererStreetNumber = ordererStreetNumber;
		}else
			throw new OrderException("invalid orderer street number");
	}

	public String getOrdererBlock() {
		return ordererBlock;
	}

	public void setOrdererBlock(String ordererBlock) throws OrderException {
		if (ordererBlock != null && ordererBlock.trim().length() > 0) {
			this.ordererBlock = ordererBlock;
		} else
			throw new OrderException("invalid orderer block");
	}
	public String getOrdererEnterance() {
		return ordererEnterance;
	}

	public void setOrdererEnterance(String ordererEnterance) throws OrderException {
		if (ordererEnterance != null && ordererEnterance.trim().length() > 0) {
			this.ordererEnterance = ordererEnterance;
		} else
			throw new OrderException("invalid orderer block entrance");
	}
	public int getOrdererFloor() {
		return ordererFloor;
	}

	public void setOrdererFloor(int ordererFloor) throws OrderException {
		if(ordererFloor > 0 ) {
			this.ordererFloor = ordererFloor;
			}else
				throw new OrderException("invalid orderer floor");
		}

	public String getOrdererAppartment() {
		return ordererAppartment;
	}

	public void setOrdererAppartment(String ordererAppartment) throws OrderException {
		if (ordererAppartment != null && ordererAppartment.trim().length() > 0) {
			this.ordererAppartment = ordererAppartment;
		} else
			throw new OrderException("invalid orderer apartment ");
	
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws OrderException {
		if (description != null && description.trim().length() > 0) {
			this.description = description;
		} else
			throw new OrderException("invalid description ");
	
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setItems(Map items) {
		this.items = items;
	}
	
	public void addItem(Item item) throws ItemException {
		if(item != null) {
			items.put(item.getId(), item);
		} else throw new ItemException("invalid item");
	}

	

}

