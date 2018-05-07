package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.DAO.ICategoryDAO;
import com.DAO.IOrderDAO;
import com.DAO.IOrderEmail;
import com.DAO.IitemDAO;
import com.DAO.ItemDAO;
import com.DAO.OrderEmail;
import com.DAO.UserDAO;
import com.exceptions.OrderException;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.model.Item;
import com.model.Order;

@Component
@Controller
public class CartController {

	@Autowired
	private IOrderDAO orderDAO;

	@Autowired
	private IitemDAO itemDAO;

	@Autowired
	private OrderEmail orderEmail;
	
	

	@RequestMapping(method = RequestMethod.GET, value = "/addItem")
	public String addItem(Model model, @RequestParam(value = "itemId", required = false) String id,
			@RequestParam(value = "itemQuantity", required = false) String quantity, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {

		HttpSession session = request.getSession(false);
		Cookie cookie = new Cookie((session.getAttribute("id")) + "&" + id, id.toString() + "&" + quantity.toString());
		cookie.setMaxAge(6000);

		response.addCookie(cookie);

		return "redirect:./cart";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/removeItem")
	public String removeItem(Model model, @RequestParam(value = "itemId", required = false) String id,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {

		HttpSession session = request.getSession(false);
		Cookie cookie = new Cookie((session.getAttribute("id")) + "&" + id, "");
		cookie.setMaxAge(0);

		response.addCookie(cookie);

		return "redirect:./cart";
	}

	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/cart")
	public String cart(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		List<Cookie> userCookies = new ArrayList<Cookie>();
		float totalPrice = 0;

		if (cookies.length > 1 && session.getAttribute("id") != null) {
			for (Cookie cookie : cookies) {

				if (cookie.getName().split("&")[0].equals(session.getAttribute("id").toString())) {
					userCookies.add(cookie);
				}
			}

			
			List<Item> items = new ArrayList<Item>();

			int totalItems = 0;

			for (Cookie cookie : userCookies) {
				String itemId = cookie.getValue().split("&")[0];
				String itemQuantity = cookie.getValue().split("&")[1];

				int id = Integer.parseInt(itemId);
				int quantity = Integer.parseInt(itemQuantity);
				totalItems += quantity;

				try {
					Item item = itemDAO.getItem(id);
					item.setQuantity(quantity);
					item.setPrice(item.getPrice() * item.getQuantity());

					totalPrice += item.getPrice();
					items.add(item);
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			session.setAttribute("userItems", totalItems);
			session.setAttribute("totalPrice", totalPrice);

			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("items", items);
			return "cart";
		} else{
			
			session.setAttribute("userItems", null);
			session.setAttribute("totalPrice", null);
			return "cart";
		}
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/checkout")
	public String showCheckout(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		List<Cookie> userCookies = new ArrayList<Cookie>();
		if (cookies.length > 1 && session.getAttribute("id") != null) {
			for (Cookie cookie : cookies) {

				if (cookie.getName().split("&")[0].equals(session.getAttribute("id").toString())) {
					userCookies.add(cookie);
				}
			}
		}
		if (userCookies.size() > 0) {
			return "checkout";
		} else {
			return "redirect:index";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkout")
	public String checkout(Model model, HttpServletRequest request, HttpServletResponse response) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String postCode = request.getParameter("postCode");
		Integer streetNumber = stringToInteger(request.getParameter("streetNumber"));
		String block = request.getParameter("block");
		String enterance = request.getParameter("enterance");
		Integer floor = stringToInteger(request.getParameter("floor"));
		String apartment = request.getParameter("apartment");
		String description = request.getParameter("description");

		HttpSession session = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		List<Cookie> userCookies = new ArrayList<Cookie>();

		HashMap<Integer, Integer> items = new HashMap<Integer, Integer>();
		if (request.getSession(false) != null && cookies != null) {
			for (Cookie cookie : cookies) {

				if (cookie.getName().split("&")[0].equals(session.getAttribute("id").toString())) {
					userCookies.add(cookie);
				}
			}

			for (Cookie cookie : userCookies) {
				String itemId = cookie.getValue().split("&")[0];
				String itemQuantity = cookie.getValue().split("&")[1];

				int id = Integer.parseInt(itemId);
				int quantity = Integer.parseInt(itemQuantity);
				items.put(id, quantity);

				// Delete Cookie
				cookie.setMaxAge(0);
				response.addCookie(cookie);

				/// Update item quantity

			}

			try {
				Order order = new Order(firstName, lastName, city, phoneNumber, postCode, street, streetNumber, block,
						enterance, floor, apartment, description,
						Integer.parseInt(session.getAttribute("id").toString()), items);
				orderDAO.makeOrder(order);

				orderEmail.sendMail(email, order);

			} catch (MailException | OrderException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			return "redirect:login";
		}
		return "redirect:successfulCheckout";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/successfulCheckout")
	public String successfulCheckout(Model model, HttpServletRequest request) {

		return "successfulCheckout";
	}

	private Integer stringToInteger(String string) {
		return string != null && !string.isEmpty() ? Integer.parseInt(string) : null;
	}

}
