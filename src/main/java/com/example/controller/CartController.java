package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.DAO.ItemDAO;
import com.model.Item;

@Controller
public class CartController {

	@RequestMapping(method = RequestMethod.GET, value = "/addItem{id}")
	public String addItem(Model model, @PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {

		HttpSession session = request.getSession(false);
		Cookie c = new Cookie((session.getAttribute("id"))+ "&" + id.toString(), id.toString());
		c.setMaxAge(1000);

		response.addCookie(c);

		return "redirect:cart";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cart")
	public String cart(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		List<Cookie> userCookies = new ArrayList<Cookie>();

		if (request.getSession(false) != null && cookies != null) {
			for (Cookie cookie : cookies) {

				if (cookie.getName().split("&")[0].equals(session.getAttribute("id").toString())) {
					userCookies.add(cookie);
				}	
			}

			List<Item> userItems = new ArrayList<Item>();

			for (Cookie cookie : userCookies) {
				int id = Integer.parseInt(cookie.getValue());
				try {
					Item item = ItemDAO.getInstance().getItem(id);
					userItems.add(item);
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			
			model.addAttribute(userItems);
			
		} else {
			return "redirect:login";
		}
		return "cart";
	}

}
