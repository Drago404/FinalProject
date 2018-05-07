package com.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DAO.ItemDAO;
import com.DAO.UserDAO;
import com.DAO.UserDAOImpl;
import com.exceptions.UserException;
import com.model.Item;
import com.model.User;

@Controller
public class AdminController {
	
	@Autowired
	private ItemDAO itemDao;
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(method=RequestMethod.GET, value="/allUsers")
	public String getAllUsers(Model model) {
		
		try{
		
		model.addAttribute("newUser", new User());
	
		UserDAOImpl dao;
		
			HashMap<String, Boolean> users = (HashMap<String, Boolean>) userDAO.getAllUsers();
			model.addAttribute("users", users);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "allUsers";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/delete")
	public String deleteUser(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		
		try {
			String email = request.getParameter("email");
			UserDAOImpl.getInstance().deleteUser(email);
			User user = UserDAOImpl.getInstance().getUserByEmail(email);
			user.setDeleted(true);
			//model.addAttribute("deleted", user.isDeleted());
			redirectAttributes.addFlashAttribute("deleted", user.isDeleted());
			
		} catch (Exception e) {
			
		}
		
		return "redirect:allUsers";
	
}
	
	
		@RequestMapping(method = RequestMethod.GET, value = "/newItem")
		public String login(Model model, HttpServletRequest request) {

		return "newItem";
	}
	
	private File convertProductPicture(MultipartFile file) throws IOException {
        File convFile = new File("C:\\items-images\\" + file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
	
	
	@RequestMapping(value = {"/newItem"}, method = RequestMethod.POST, consumes = "multipart/form-data")
    public String addProduct(Model model, HttpServletRequest request, @ModelAttribute("theItem") Item item,
    		@RequestParam("picture") MultipartFile picture,
    		@RequestParam("brand") long brand,
    		@RequestParam("category") long category) {
       
	
		HashMap<Long, String> categories = itemDao.getAllCategories();
		model.addAttribute("categories", categories);
		HashMap<Long, String> brands = itemDao.getAllBrands();
		model.addAttribute("brands", brands);
		
		
		String name = request.getParameter("name");
		//long brand = Long.parseLong(request.getParameter("brand"));
		float price = Float.parseFloat(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		//long category = Long.parseLong(request.getParameter("category"));
		String description = request.getParameter("description");
		

        String mimetype = picture.getOriginalFilename().split("\\.")[1];
        String type = mimetype.split("/")[0];
        if (!type.equals("jpg") && !type.equals("png") && !type.equals("jpeg"))
            return "addItem";
        try {
            File newFile = convertProductPicture(picture);
            Item product = new Item(name, brand, price, quantity, category, description, newFile.getPath());
            itemDao.addItem(product);
        } catch (IOException e) {
        	  return "addItem";
        } 
          return "index";
    }
	
	
}
