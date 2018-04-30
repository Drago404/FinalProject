package com.example.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;


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
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String birthDate = request.getParameter("dateOfBirth");

		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		LocalDate date = LocalDate.parse(birthDate, formatter);

		try {
			
			User user = new User(firstName, lastName, email, pass, date);

			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(pass);
			user.setDateOfBirth(date);

			UserDAOImpl.getInstance().register(user);

		} catch (UserException | SQLException e) {
			e.printStackTrace();
			return "register";
		}
		return "redirect:index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(Model model, HttpServletRequest request) {

		return "login";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String checkLogin(Model model, HttpServletRequest request, HttpServletResponse response)
			throws UserException {

		String email = request.getParameter("email");
		String password = request.getParameter("pass");

		try {
			UserDAOImpl.getInstance().login(email, password);
			User user = UserDAOImpl.getInstance().getUser(email, password);
			model.addAttribute(user);

		} catch (SQLException e) {
			e.printStackTrace();
			return "register";
		}

		return "redirect:index";
	}

}

				
	

	

