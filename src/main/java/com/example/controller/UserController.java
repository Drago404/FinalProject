package com.example.controller;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.DAO.UserDAOImpl;
import com.exceptions.UserException;
import com.model.User;


@Controller
public class UserController {
	

	
	
	  @RequestMapping(value = "/register", method = RequestMethod.GET)
	  public String showRegister(HttpServletRequest request, HttpServletResponse response) {
	    return "register";
	  }
	  
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request) {
            System.out.println(" tuka sym e");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String birthDate = request.getParameter("dateOfBirth");

		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		LocalDate date = LocalDate.parse(birthDate, formatter);
		User user = new User();

		try {
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(pass);
			user.setDateOfBirth(date);

			UserDAOImpl.getInstance().register(user);
		} catch (UserException | SQLException e) {
			e.printStackTrace();
		}
		return "redirect:index";
	}
	
	
	
	



	
}
