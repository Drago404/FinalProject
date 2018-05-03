package com.example.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DAO.ItemDAO;
import com.DAO.UserDAOImpl;
import com.exceptions.UserException;
import com.model.Item;
import com.model.User;

@Controller
public class AdminController {

	@RequestMapping(method=RequestMethod.GET, value="/allUsers")
	public String getAllUsers(Model model) {
		
		model.addAttribute("newUser", new User());
	
		UserDAOImpl dao;
		try {
			dao = UserDAOImpl.getInstance();
			HashMap<String, Boolean> users = dao.getAllUsers();
			model.addAttribute("users", users);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "allUsers";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/delete")
	public String deleteUser(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		String email = request.getParameter("email");
		try {
			UserDAOImpl.getInstance().deleteUser(email);
			User user = UserDAOImpl.getInstance().getUserByEmail(email);
			user.setDeleted(true);
			//model.addAttribute("deleted", user.isDeleted());
			redirectAttributes.addFlashAttribute("deleted", user.isDeleted());
			
		} catch (SQLException | UserException e) {
			e.printStackTrace();
		}
		
		return "redirect:allUsers";
	
}
	
	
}
