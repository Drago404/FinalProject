package com.example.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.DAO.IitemDAO;
import com.DAO.ItemDAO;
import com.DAO.UserDAO;
import com.DAO.UserDAOImpl;
import com.exceptions.UserException;
import com.model.Item;
import com.model.User;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private IitemDAO itemDAO;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister(HttpServletRequest request, HttpServletResponse response) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request) {
		
		try {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String birthDate = request.getParameter("dateOfBirth");

		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		LocalDate date = LocalDate.parse(birthDate, formatter);

		
			
			User user = new User(firstName, lastName, email, pass, date);

			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(pass);
			user.setDateOfBirth(date);

			userDAO.register(user);

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
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public String logout(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		session.invalidate();

		return "redirect:index";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String checkLogin(Model model, HttpServletRequest request, HttpServletResponse response){

		try {
		String password = request.getParameter("pass");
		String email = request.getParameter("email");

		
			UserDAOImpl.getInstance().login(email, password);
			User user = UserDAOImpl.getInstance().getUser(email, password);
			
			if(UserDAOImpl.getInstance().checkIsDeleted(email)) {
				return "redirect:login";
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("id", user.getId());
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("isAdmin", user.isAdmin());
			//session.setAttribute("wishlistNumber", userDAO.getWishlist((int) user.getId()).size());
			
			//check for admin
			if(UserDAOImpl.getInstance().checkForAdmin(user)) {
				user.setAdmin(true);
			}
			
		} catch (UserException |SQLException e) {
			e.printStackTrace();
			return "redirect:login";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "redirect:index";
	}
	

//	@RequestMapping(method = RequestMethod.GET, value = "/wishlist")
//	public String wishlist(Model model, HttpServletRequest request) {
//		
//		HttpSession session = request.getSession(false);
//		int userId = Integer.parseInt(session.getAttribute("id").toString());
//		
//		List<Item> wishlist = new ArrayList<Item>();
//		try {
//			List<Integer> itemsIds = userDAO.getWishlist(userId);
//			for(Integer id : itemsIds){
//				Item item =itemDAO.getItem(id.intValue());
//				wishlist.add(item);
//			}
//			session.setAttribute("wishlistNumber", itemsIds.size());
//			model.addAttribute("wishlist",wishlist);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return "wishlist";
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/addWishlist")
//	public String addToWishlist(Model model, HttpServletRequest request,@RequestParam(value = "itemId", required = false) String id) {
//		
//		HttpSession session = request.getSession(false);
//		
//		int userId = Integer.parseInt(session.getAttribute("id").toString());
//		int itemId = Integer.parseInt(id);
//		
//		try {
//			userDAO.addToWishlist(userId, itemId);
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//		
//		return "redirect:wishlist";
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/removeWishlist")
//	public String removeItem(Model model, @RequestParam(value = "itemId", required = false) String id,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		HttpSession session = request.getSession(false);
//		int userId = Integer.parseInt(session.getAttribute("id").toString());
//		int itemId = Integer.parseInt(id);
//		
//		try {
//			userDAO.removeFromWishlist(userId,itemId);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return "redirect:wishlist";
//	}

	@RequestMapping(method = RequestMethod.GET, value = "/wishlist")
	public String wishlist(Model model, HttpServletRequest request) {
		try {
		HttpSession session = request.getSession(false);
		int userId = Integer.parseInt(session.getAttribute("id").toString());
		
		List<Item> wishlist = new ArrayList<Item>();
		
			List<Integer> itemsIds = userDAO.getWishlist(userId);
			for(Integer id : itemsIds){
				Item item =itemDAO.getItem(id.intValue());
				wishlist.add(item);
			}
			session.setAttribute("wishlistNumber", itemsIds.size());
			model.addAttribute("wishlist",wishlist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "wishlist";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/addWishlist")
	public String addToWishlist(Model model, HttpServletRequest request,@RequestParam(value = "itemId", required = false) String id) {
		try {
		HttpSession session = request.getSession(false);
		
		int userId = Integer.parseInt(session.getAttribute("id").toString());
		int itemId = Integer.parseInt(id);
		
		
			userDAO.addToWishlist(userId, itemId);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
		}
		
		return "redirect:wishlist";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/removeWishlist")
	public String removeItem(Model model, @RequestParam(value = "itemId", required = false) String id,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
		HttpSession session = request.getSession(false);
		int userId = Integer.parseInt(session.getAttribute("id").toString());
		int itemId = Integer.parseInt(id);
		
	
			userDAO.removeFromWishlist(userId,itemId);
		} catch (SQLException e) {
		} catch (Exception e) {
		}

		return "redirect:wishlist";
	}

	
	
	@RequestMapping(method = RequestMethod.GET, value = "/error")
	public String error(Model model, HttpServletRequest request) {

		return "error";
	}
	

}
