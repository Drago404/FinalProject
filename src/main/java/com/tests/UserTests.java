package com.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;

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
	public void testBadPassword() throws SQLException, UserException {
		User user = new User("toni@abv.bg", "1234");
		Boolean  loginCheck = dao.login(user.getEmail(), user.getPassword());
		
		
		assertFalse(loginCheck);
	}
	
	@Test
	public void testBadEmail() throws SQLException, UserException {
		User user = new User("toniiiii@abv.bg", "123");
		Boolean  loginCheck = dao.login(user.getEmail(), user.getPassword());
		
		
		assertFalse(loginCheck);
	}
	
//	@Test
//	public void testRegister() throws SQLException, UserException {
//		LocalDate localDate = LocalDate.now();
//		User user = new User("Anton", "Petrov", "anton@abv.bg", "123", localDate);
//
//		dao.register(user);
//		Boolean loginCheck = dao.login(user.getEmail(), user.getPassword());
//		assertTrue(loginCheck);
//	}
	
	
	@Test(expected = UserException.class)
	public void testUser() throws UserException, SQLException {
		LocalDate localDate = LocalDate.now();
		User user = new User("Ivan", "Petrov", "ivanov@abv.bg", "123", localDate);
		dao.register(user);
		Boolean checkUser = dao.checkIfUserExists(user);
		
		assertFalse(checkUser);
	}
	
}
