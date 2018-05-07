package com.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.db.DBConnection;
import com.exceptions.UserException;
import com.model.Item;
import com.model.Order;
import com.model.User;

@Component
public class UserDAOImpl implements UserDAO {
	
	private	static final String INSERT_USER = "insert into users(first_name,last_name,email,password,date_of_birth) "
			+ "values (?, ?, ?, sha1(?), ?)";
	private static final String CHECK_USER_IF_EXISTS = "select * from users where email =?";
	private static final String CHECK_LOGIN = "select * from users where email=? and password=sha1(?)";
	private static final String GET_ALL_USERS = "select * from users";
	private static final String CHECK_FOR_ADMIN = "select id,first_name from users where is_admin=1";
	private static final String GET_USER_ID = "select * from users where email=?";
	private static final String DELETE_USER = "update users set deleted=1 where id=?";
	private static final String IS_DELETED = "select deleted from users where email=?";
	private static final String ADD_TO_WISHLIST = "INSERT INTO favourite_items (users_id,item_id) values(?,?)";
	private static final String GET_WISHLIST = "SELECT (item_id) from  favourite_items WHERE users_id =?";
	private static final String REMOVE_FROM_WISHLIST = "DELETE FROM favourite_items WHERE users_id =? AND item_id = ?;";
	private Connection conn;
	private HashMap<String, Boolean> allUsers;
	private static UserDAOImpl userDao;
	
	
	
	private UserDAOImpl() throws SQLException{
		this.conn = DBConnection.getInstance().getConnection();
		allUsers = new HashMap<String, Boolean>();
	}
	
	@Override
	public void register(User user) throws UserException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
		if (checkIfUserExists(user)) {
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setDate(5, Date.valueOf(user.getDateOfBirth()));
			String fullName = user.getEmail() + " - " + user.getFirstName() + " " + user.getLastName();
			
			synchronized (this) {
				stmt.executeUpdate();
				allUsers.put(fullName, false);
			}
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				long userId= rs.getLong(1);
				user.setId(userId);
			}
			
			stmt.close();
			return;
		} else {
			stmt.close();
			throw new UserException("User already exists");
		}
	}
	
	
	@Override
    public boolean checkIfUserExists(User user) throws UserException, SQLException {
		 PreparedStatement  stmt = conn.prepareStatement(CHECK_USER_IF_EXISTS);
		 stmt.setString(1, user.getEmail());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			stmt.close();
			rs.close();
			return false;
		}
			stmt.close();
			rs.close();
			return true;
	}
	
	public User getUser(String email, String password) throws UserException{

		try {
			PreparedStatement stmt = conn.prepareStatement(CHECK_LOGIN);
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				long id = rs.getLong("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				LocalDate birthDate = rs.getDate("date_of_birth").toLocalDate();
				Boolean is_Admin = rs.getBoolean("is_admin");
				
				User user = new User(id, firstName, lastName, email, password, birthDate, is_Admin);
				stmt.close();
				rs.close();
				return user;
			} else{
				throw new UserException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean login(String email, String password) throws SQLException {
		try {
			PreparedStatement stmt = conn.prepareStatement(CHECK_LOGIN);
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				rs.close();
				stmt.close();
				return true;
			}
			stmt.close();
			rs.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public HashMap<String, Boolean> getAllUsers() throws SQLException {
		PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement(GET_ALL_USERS);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					Boolean is_deleted = rs.getBoolean("deleted");
					
					String fullName = email + " - " + firstName + " " +  lastName;
					User user = new User(firstName, lastName, email, password);
					if(is_deleted) {
						user.setDeleted(true);
					}
					//synchr
					allUsers.put(fullName, is_deleted);
					
				}
			} catch (SQLException | UserException e) {
				e.printStackTrace();
			}
			
		return allUsers;
	}

	@Override
	public boolean checkForAdmin(User user) {
		try {
			PreparedStatement stmt = conn.prepareStatement(CHECK_FOR_ADMIN);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString("first_name");
				Long id = rs.getLong("id");
				if (firstName.equals("admin")) {
					user.setAdmin(true);
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public void deleteUser(String email) {
		try {
			PreparedStatement stmt = conn.prepareStatement(GET_USER_ID);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				PreparedStatement stmt2 = conn.prepareStatement(DELETE_USER);
				stmt2.setLong(1, id);
				stmt2.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User getUserByEmail(String email) throws UserException{


		try {
			PreparedStatement stmt = conn.prepareStatement(GET_USER_ID);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				long id = rs.getLong("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String password = rs.getString("password");
				Boolean is_deleted = rs.getBoolean("deleted");
				
				User user = new User(id, firstName, lastName, email, password, is_deleted);
				user.setDeleted(true);
				stmt.close();
				rs.close();
				return user;
			} else{
				throw new UserException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean checkIsDeleted(String email) {
		PreparedStatement stmt;
		Boolean isDeleted = false;
		try {
			stmt = conn.prepareStatement(GET_USER_ID);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				isDeleted = rs.getBoolean("deleted");
				return isDeleted;
			}
			return isDeleted;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	
	public void addToWishlist(int userId, int itemId) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement(ADD_TO_WISHLIST);
		stmt.setInt(1, userId);
		stmt.setInt(2, itemId);
		
		stmt.executeUpdate();
		
		
		
	}
	
	@Override
	public void removeFromWishlist(int userId, int itemId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(REMOVE_FROM_WISHLIST);
		stmt.setInt(1, userId);
		stmt.setInt(2, itemId);
		stmt.executeUpdate();
		
	}
	
	@Override
	public List<Integer> getWishlist(int userId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(GET_WISHLIST);
		stmt.setInt(1, userId);
		List<Integer> wishlist = new ArrayList<Integer>();
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			
			wishlist.add(rs.getInt("item_id"));
		}
		
		
		return wishlist;
	}
	
	
	
	
	public static UserDAOImpl getInstance() throws SQLException {
		if (userDao == null) {
			userDao = new UserDAOImpl();
		}
		return userDao;
	}

	

	
	
}
