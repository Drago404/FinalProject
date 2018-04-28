package com.example.controller;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.DAO.UserDAOImpl;
import com.exceptions.UserException;
import com.model.User;


@Controller
public class UserController {
	

	private UserDAOImpl userDao;
	

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public String register(Model model, HttpServletRequest request) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String birthDate = request.getParameter("dateOfBirth");

		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		LocalDate date = LocalDate.parse(birthDate, formatter);
		User user = null;
		try {

			user = new User(firstName, lastName, email, pass, date);
		} catch (UserException e) {
			e.printStackTrace();
		}

		try {
			userDao.register(user);

		} catch (UserException | SQLException e) {
			e.printStackTrace();
			return "redirect:register.jsp";
		}
		return "login";
		
		
	
}


	
}
