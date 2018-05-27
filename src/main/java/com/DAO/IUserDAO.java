package com.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.exceptions.UserException;
import com.model.User;

public interface IUserDAO {
	
	void register(User user) throws UserException, SQLException;

	boolean checkIfUserExists(User user) throws UserException, SQLException;
	
	boolean login(String email, String password) throws SQLException;
	
	public Map<String, Boolean> getAllUsers() throws SQLException;

	public boolean checkForAdmin(User user);
	
	void addToWishlist(int userId, int itemId) throws SQLException;

	void removeFromWishlist(int userId, int itemId) throws SQLException;

	List<Integer> getWishlist(int userId) throws SQLException;

	void deleteUser(String email);

	User getUserByEmail(String email) throws UserException;

	User getUser(String email, String password) throws UserException;

	boolean checkIsDeleted(String email);

}
