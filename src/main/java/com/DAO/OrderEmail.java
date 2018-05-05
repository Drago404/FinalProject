package com.DAO;

import java.sql.SQLException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.exceptions.OrderException;
import com.model.Item;
import com.model.Order;

import javax.activation.*;

@Service
public class OrderEmail {
	
	@Autowired
	private IitemDAO itemDao;
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String email, Order order) throws MailException, OrderException, SQLException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom("technomarketshoporders@gmail.com");
		mail.setSubject("Order № ");

		String orderFloor;
		String orderStreetNumber;

		if (order.getOrdererFloor() == null) {
			orderFloor = "";
		} else {
			orderFloor = order.getOrdererFloor().toString();
		}
		if (order.getOrdererStreetNumber() == null) {
			orderStreetNumber = "";
		} else {
			orderStreetNumber = order.getOrdererStreetNumber().toString();
		}

		
		Map<Integer,Integer> orderedItems = order.getItems();
		

		Item item;
		
		StringBuilder items = new StringBuilder();
		
		float totalprice = 0f;
		for (Integer itemId : orderedItems.keySet()) {
			item =itemDao.getItem(itemId.intValue());
			String itemName = item.getName();
			int quantity = orderedItems.get(itemId);
			totalprice += item.getPrice()*quantity;
			items.append("Product: "+itemName +"/ Quantity: x"+ quantity + "/ Price: " + (item.getPrice() * quantity) +" lv." + "\n");
		}
		
		items.append("Total price: " + totalprice + " lv.");
		
		
		mail.setText("You ordered from Technomarket :\n\nNAME: " + order.getOrdererFirstName() + " "
				+ order.getOrdererLastName() + "\nPHONE: " + order.getOrdererPhone() + "\nADDRESS OF DELIVERY: City: "
				+ order.getOrdererCity() + "  Postcode: " + order.getOrdererPostCode() + "  Street: "
				+ order.getOrdererStreet() + "  №" + orderStreetNumber + "  Block: " + order.getOrdererBlock()
				+ "  Enterance: " + order.getOrdererEnterance() + "  Floor: " + orderFloor + "  Appartment: "
				+ order.getOrdererAppartment() + "\nADDITIONAL INFORMATION: " + order.getDescription() + "\n\nOrdered Products :\n"+ items);
		
		javaMailSender.send(mail);

	}

}
