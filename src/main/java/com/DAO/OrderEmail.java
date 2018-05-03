package com.DAO;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.model.Order;

import javax.activation.*;

//@Service
public class OrderEmail implements IOrderEmail {
	
//	private JavaMailSender javaMailSender;
//	
//	@Autowired
//	public  OrderEmail(JavaMailSender javaMailSender){
//		this.javaMailSender = javaMailSender;
//	}
//	
//	
//	
//	@Override
//	public void sendMail(String email, Order order)throws MailException{
//		
//		
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(email);
//		mail.setFrom("technomarketshoporders@gmail.com");
//		mail.setSubject("Order Number");
//		mail.setText("You ordered from Technomarket :\n" + order.getOrdererFirstName() + "/n"+ order.getOrdererLastName()
//		+ "/n"+ order.getOrdererPhone() + "/n"+order.getOrdererCity()  + "/n"+order.getOrdererPostCode() + "/n"+order.getOrdererStreet() + "/n"+
//		order.getOrdererStreetNumber() +"/n"+ order.getOrdererBlock() +"/n"+ order.getOrdererEnterance() + "/n"+order.getOrdererFloor()+ "/n"+
//		order.getOrdererAppartment() +"/n"+ order.getDescription() );
//		
//		for (int i = 0; i < 100; i++) {
//			
//		}	System.out.println(mail.getText());
//	
//		
//		
//		javaMailSender.send(mail);
//		
//	}
	
}
