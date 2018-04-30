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

import com.db.DBConnection;
import com.exceptions.UserException;
import com.model.User;

public class UserDAOImpl implements UserDAO {
	
	private	static final String INSERT_USER = "insert into users(first_name,last_name,email,password,date_of_birth) "
			+ "values (?, ?, ?, sha1(?), ?)";
	private static final String CHECK_USER_IF_EXISTS = "select * from users where email =?";
	private static final String CHECK_LOGIN = "select * from users where email=? and password=sha1(?)";
	private static final String GET_ALL_USERS = "select * from users";
	private static final String CHECK_FOR_ADMIN = "select email,password from users where email=? and password=sha1(?)";
	private static final String MAKE_ADMIN = "update users set is_admin=1 where id=?"; 
	private Connection conn;
	private Map<String, User> allUsers;
	private static UserDAOImpl userDao;
	
	
	
	private UserDAOImpl() throws SQLException{
		this.conn = DBConnection.getInstance().getConnection();
		allUsers = new HashMap<String, User>();
	}

	
	@Override
	public boolean register(User user) throws UserException, SQLException {
		PreparedStatement stmt = conn.prepareStatement(INSERT_USER);
		if (checkIfUserExists(user)) {
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setDate(5, Date.valueOf(user.getDateOfBirth()));
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			long primaryKey = rs.getLong(1);
			user.setId(primaryKey);
			synchronized (this) {
				stmt.executeUpdate();
				allUsers.put(user.getEmail(), user);
			}
			stmt.close();
			return true;
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
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				LocalDate birthDate = rs.getDate("date_of_birth").toLocalDate();
				Boolean is_Admin = rs.getBoolean("is_admin");
				
				User user = new User(firstName, lastName, email, password, birthDate, is_Admin);
				stmt.close();
				rs.close();
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public Map<String, User> getAllUsers() throws SQLException {
		PreparedStatement stmt;
		if (allUsers.isEmpty()) {
			try {
				stmt = conn.prepareStatement(GET_ALL_USERS);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					LocalDate birthDate = rs.getDate("date_of_birth").toLocalDate();
					Boolean is_Admin = rs.getBoolean("is_admin");

					User user = new User(firstName, lastName, email, password, birthDate, is_Admin);
					allUsers.put(user.getEmail(), user);
					stmt.close();
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (UserException e) {
				e.printStackTrace();
			}
			
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
				String password = rs.getString("password");
				if (firstName.equals(user.getFirstName()) && user.getFirstName().equals("admin")
						&& password.equals(user.getPassword()) && user.getPassword().equals("admin")) {
					user.setAdmin(true);
					PreparedStatement stmt2 = conn.prepareStatement(MAKE_ADMIN);
					stmt.setLong(1, user.getId());
					stmt2.executeUpdate();

					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	public static UserDAOImpl getInstance() throws SQLException {
		if (userDao == null) {
			userDao = new UserDAOImpl();
		}
		return userDao;
	}
	
}
