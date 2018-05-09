package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.DAO.CategoryDAO;
import com.DAO.UserDAO;
import com.config.SpringWebConfig;
import com.model.Category;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringWebConfig.class)
public class CategoryTest {

	@Autowired
	CategoryDAO dao;
	
	@Test
	public void testCategory() {
		Category cat = new Category("TV");
		Category category = dao.getCategory(1);
		
		assertEquals(cat.getName(), category.getName());
		
	}

}
