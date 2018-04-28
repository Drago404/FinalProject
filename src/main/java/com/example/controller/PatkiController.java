package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Patka;
import com.example.model.PatkiDAO;

@Controller
public class PatkiController {
	
	@Autowired
	private PatkiDAO patkiDao;
	
	@RequestMapping(method=RequestMethod.GET, value="/patki")
	public String allPatki(Model model) {
		String name = "Stamen";
		model.addAttribute("name", name);
		
		model.addAttribute("newPatka", new Patka());
		//davam danni za pokazvane
		List<Patka> patki = patkiDao.getAll();
		model.addAttribute("patki", patki);
		
		// name of view deto shte gi pokazva
		return "patki";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/patki")
	public String addNewPatka(Model model, @ModelAttribute Patka newPatka, HttpServletRequest request) {
		if (newPatka.getGradusi() < 0) {
			return "redirect:index";
		}
		request.getSession().setAttribute("patka", new Patka());
		patkiDao.addPatka(newPatka);
		
		return allPatki(model);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/patki/{id}")
	public String viewPatka(Model model, @PathVariable Integer id) {
		Patka p = patkiDao.getPatkaByIndex(id);
		
		model.addAttribute(p);
		
		return "patkaDetail";
	}
}

	
