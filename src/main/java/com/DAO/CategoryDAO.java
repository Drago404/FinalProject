package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.db.DBConnection;
import com.model.Category;
import com.model.Item;

@Component
public class CategoryDAO implements ICategoryDAO{

	private static final String ALL_CATEGORIES = "select name, category_id from category";
	private static final String GET_CATEGORY_BY_ID = "SELECT * from category WHERE id = ?";
	private static HashMap<String, Integer> allCategories = new HashMap<>();
	private Connection conn;
	private static CategoryDAO cat;

	private CategoryDAO() throws SQLException {
		this.conn = DBConnection.getInstance().getConnection();
	}

	public synchronized HashMap<String, Integer> getAllCategories() {
		if (allCategories.isEmpty()) {
			try {
				PreparedStatement stmt = conn.prepareStatement(ALL_CATEGORIES);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String name = rs.getString("name");
					int categoryId = rs.getInt("category_id");

					allCategories.put(name, categoryId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return allCategories;
	}

	public Category getCategory(int categoryId) {
		Category category = new Category();
		try {
			PreparedStatement stmt;
			stmt = conn.prepareStatement(GET_CATEGORY_BY_ID);
			stmt.setInt(1, categoryId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return category;
	}

//	public static CategoryDAO getInstance() throws SQLException {
//		if (cat == null) {
//			cat = new CategoryDAO();
//		}
//		return cat;
//	}

}
