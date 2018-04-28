package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("product")
public class ProductsController {

	@RequestMapping(value="/products",method = RequestMethod.GET)
	public String products(Model model, HttpServletRequest request) {
		//Product p = new Product();
		Car pejo = new Car();
		model.addAttribute(pejo);
		System.out.println(request.getAttribute("test"));
		
		return "products";
	}
	
	@RequestMapping(value="/products/{product_id}",method = RequestMethod.GET)
	public String products(Model model, @PathVariable("product_id") Integer productId) {
//		Product product = ...get product by id from somewhere 
//		model.addAttribute("product", product);
		System.out.println(productId);
		
		return "product";
	}
	
	@RequestMapping(value="/products",method = RequestMethod.POST)
	public String products(@ModelAttribute Car p) {
		
		//add the new product somewhere
		
		System.out.println(p.getModel());
		System.out.println(p.getColor());
		
		// redirect to home page
		return "redirect:index.html";
	}
	
}
