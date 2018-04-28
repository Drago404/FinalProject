package com.technomarket.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.UserDAOImpl;
import com.exceptions.UserException;
import com.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			if(UserDAOImpl.getInstance().register(user)) {
				response.getWriter().println("Register successful");
			}
			else response.getWriter().println("ne");
		} catch (UserException | SQLException e) {
			response.getWriter().println("User already exists");
			
		}
		
	}

}
