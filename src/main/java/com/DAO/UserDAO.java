package com.DAO;

import java.sql.SQLException;
import java.util.Map;

import com.exceptions.UserException;
import com.model.User;

public interface UserDAO {
	
	 void register(User user) throws UserException, SQLException;

	boolean checkIfUserExists(User user) throws UserException, SQLException;
	
	boolean login(String email, String password) throws SQLException;
	
	public Map<String, User> getAllUsers() throws SQLException;

}
