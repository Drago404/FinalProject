package com.example.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;


import com.DAO.UserDAOImpl;
import com.exceptions.UserException;
import com.model.User;


@Controller
public class UserController {
	

	

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public String register(Model model, HttpServletRequest request) {
		String firstName = request.getParameter("firstName");
		model.addAttribute("firstname",firstName);
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
			UserDAOImpl.getInstance().register(user);

		} catch (UserException | SQLException e) {
			e.printStackTrace();
			return "redirect:register";
		}
		return "register";
}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(Model model, HttpServletRequest request) {
			
		return "login";
}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String checkLogin(Model model, HttpServletRequest request, HttpServletResponse response) throws UserException {

		String email = request.getParameter("email");
		String password = request.getParameter("pass");
	
		try {
			if(UserDAOImpl.getInstance().login(email, password)){
				
				for(int i = 0; i < 1000000; i++){
					System.out.println("vlizam v proverkat");
				}
				
				User user = UserDAOImpl.getInstance().getUser(email, password);
				model.addAttribute(user);
				return "./index";
			} else{
				try {
					response.sendRedirect("./tv");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		return "login";
}
	
}
