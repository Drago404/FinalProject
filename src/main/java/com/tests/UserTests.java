package com.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.DAO.UserDAO;
import com.DAO.UserDAOImpl;
import com.config.SpringWebConfig;
import com.exceptions.UserException;
import com.model.User;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringWebConfig.class)
public class UserTests {

	
	@Autowired
	UserDAO dao;
	
	@Test
	public void test() throws SQLException, UserException {
		User user = new User("toni@abv.bg", "1234");
		Boolean  loginCheck = dao.login(user.getEmail(), user.getPassword());
		
		
		assertThat(loginCheck, is(false));
	}

}
