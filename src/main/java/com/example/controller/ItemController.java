package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.DAO.ItemDAO;
import com.model.Item;

@Controller
public class ItemController  {
    
 
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public String getItem(Model model, @PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		

		
		ItemDAO dao = ItemDAO.getInstance();
		Item item = dao.getItem(id);
		
		model.addAttribute(item);
		
		
		
		return "item";
	}
	
	

	@RequestMapping(method=RequestMethod.GET, value="/search/{text}")
	public String searchItems(Model model, @PathVariable("text") String text) throws SQLException {
		model.addAttribute("newItem", new Item());
		
		ItemDAO dao = ItemDAO.getInstance();
		List<Item> items = dao.getItemsByName(text);
		
		model.addAttribute("items",items);
		
		return "search";
	}

}
