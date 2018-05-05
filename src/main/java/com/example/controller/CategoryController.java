package com.example.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.DAO.CategoryDAO;
import com.DAO.ICategoryDAO;
import com.DAO.IitemDAO;
import com.DAO.ItemDAO;
import com.model.Category;
import com.model.Item;

@Component
@Controller
public class CategoryController {
	
	@Autowired
	private ICategoryDAO categoryDAO;
	
	@Autowired
	private IitemDAO itemDAO;
	
	@RequestMapping(method=RequestMethod.GET, value="/tv")
	public String item(Model model) throws SQLException {
		
		model.addAttribute("newItem", new Item());
		
		Category category = categoryDAO.getCategory(1);
		
		List<Item> items = itemDAO.getItemsByCategory(category);
		model.addAttribute("items",items);
			
		// name of view deto shte gi pokazva
		return "tv";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/laptop")
	public String laptop(Model model) throws SQLException {
		
		model.addAttribute("newItem", new Item());
		
		Category category = categoryDAO.getCategory(2);
		
		List<Item> items = itemDAO.getItemsByCategory(category);
		model.addAttribute("items",items);
			
		// name of view deto shte gi pokazva
		return "tv";
	}
	
//	@RequestMapping(method=RequestMethod.GET, value="{id}")
//	public String viewPatka(Model model, @PathVariable Integer id) throws SQLException {
//		ItemDAO dao = ItemDAO.getInstance();
//		Item item = dao.getItem(id);
//		
//		model.addAttribute(item);
//		
//		return "neo-led-49418-uhd-sw-09162977";
//	}
	
	
}
