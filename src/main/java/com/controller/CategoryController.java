package com.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(method = RequestMethod.GET, value = "/tv")
	public String tv(Model model) {

		try {

			model.addAttribute("category", "TVs");

			Category category = categoryDAO.getCategory(1);

			List<Item> items = itemDAO.getItemsByCategory(category);
			model.addAttribute("items", items);

		} catch (Exception e) {
			return "error";
		}

		return "category";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/laptop")
	public String laptop(Model model) {

		try {

			model.addAttribute("category", "Laptops");
			Category category = categoryDAO.getCategory(2);

			List<Item> items = itemDAO.getItemsByCategory(category);
			model.addAttribute("items", items);

		} catch (Exception e) {
			return "error";
		}

		return "category";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/phone")
	public String phone(Model model) {

		try {

			model.addAttribute("category", "Smartphones");
			Category category = categoryDAO.getCategory(3);

			List<Item> items = itemDAO.getItemsByCategory(category);
			model.addAttribute("items", items);

		} catch (Exception e) {
			return "error";
		}

		return "category";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/tvbrand")
	public String tvAndBrand(Model model,@RequestParam(value = "brandId", required = true) String id) {
		
		int brandId = Integer.parseInt(id.toString());
		
		try {

			model.addAttribute("category", "TVs");

			Category category = categoryDAO.getCategory(1);

			List<Item> items = itemDAO.getItemsByCategoryAndBrand(category, brandId);
			model.addAttribute("items", items);

		} catch (Exception e) {
			return "error";
		}

		return "category";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/laptopbrand")
	public String laptopAndBrand(Model model,@RequestParam(value = "brandId", required = true) String id) {
		
		int brandId = Integer.parseInt(id.toString());
		
		try {

			model.addAttribute("category", "Laptops");

			Category category = categoryDAO.getCategory(2);

			List<Item> items = itemDAO.getItemsByCategoryAndBrand(category, brandId);
			model.addAttribute("items", items);

		} catch (Exception e) {
			return "error";
		}

		return "category";
	}
	
}
