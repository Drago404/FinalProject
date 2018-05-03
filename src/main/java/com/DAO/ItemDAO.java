package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.db.DBConnection;
import com.model.Category;
import com.model.Item;

@Component
public class ItemDAO implements IitemDAO{

	private static final String SHOW_ALL_ITEMS_BY_CATEGORY = "SELECT * FROM items WHERE category_id = ?";
	private static final String ADD_PRODUCT = "insert into items(name,price,description,quantity,pictureUrl,category_id,brand_id)"
			+ "values(?, ?, ?, ?, ?, ?,?) ";
	private static final String SELECT_SPECIFIC_ITEM = "SELECT * from items WHERE id = ?";
	private static final String SELECT_ITEM_BY_NAME = "SELECT * FROM items WHERE name LIKE ?";
	private Connection conn;
	public static Map<Long, Item> allItems;

	
	/////////
	private ICategoryDAO categoryDAO;

	public ItemDAO() throws SQLException {
		this.conn = DBConnection.getInstance().getConnection();
		allItems = new HashMap<Long, Item>();
	}



	public List<Item> getItemsByCategory(Category category) throws SQLException {
		int categoryID = category.getId();
		PreparedStatement stmt = conn.prepareStatement(SHOW_ALL_ITEMS_BY_CATEGORY);
		stmt.setInt(1, categoryID);
		ResultSet rs = stmt.executeQuery();

		List<Item> itemsList = new ArrayList<Item>();

		while (rs.next()) {

			Item item = new Item();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getFloat("price"));
			item.setDescription(rs.getString("description"));
			item.setQuantity(rs.getInt("quantity"));
			item.setCategoryId(rs.getLong("category_id"));
			item.setBrandId(rs.getLong("brand_id"));
			item.setPictureUrl(rs.getString("pictureUrl"));
			itemsList.add(item);

		}
		stmt.close();

		return itemsList;

	}
	
	public List<Item> getItemsByName(String text) {
		
		StringBuilder sb = new StringBuilder(text);
		sb.insert(0, "%");	
		sb.append("%");
		text = sb.toString();
		
		
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ITEM_BY_NAME);
			stmt.setString(1, text);
			ResultSet rs = stmt.executeQuery();
			List<Item> itemsList = new ArrayList<Item>();
			
			while (rs.next()) {

				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getFloat("price"));
				item.setDescription(rs.getString("description"));
				item.setQuantity(rs.getInt("quantity"));
				item.setCategoryId(rs.getLong("category_id"));
				item.setBrandId(rs.getLong("brand_id"));
				item.setPictureUrl(rs.getString("pictureUrl"));
				itemsList.add(item);
			}
			stmt.close();

			return itemsList;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public Item getItem(int itemId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(SELECT_SPECIFIC_ITEM);
		stmt.setInt(1, itemId);
		ResultSet rs = stmt.executeQuery();

		Item item = new Item();

		if (rs.next()) {
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getFloat("price"));
			item.setDescription(rs.getString("description"));
			item.setQuantity(rs.getInt("quantity"));
			item.setCategoryId(rs.getLong("category_id"));
			item.setBrandId(rs.getLong("brand_id"));
			item.setPictureUrl(rs.getString("pictureUrl"));

			StringBuilder des = new StringBuilder(item.getDescription());

			for (int index = 0; index < des.length(); index++) {
				if (des.charAt(index) == '•') {
					des.append(System.getProperty("line.separator"));
					index++;
				}
			}
			item.setDescription(des.toString());
			
		}
		stmt.close();
		return item;
	}

	// admin
	public void addItem(Item i) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			int categoryId = categoryDAO.getAllCategories().get(i.getCategoryId());
//			int brandId = categoryDAO.getAllCategories().get(i.getCategoryId());
			stmt = conn.prepareStatement(ADD_PRODUCT);
			stmt.setString(1, i.getName());
			stmt.setFloat(2, i.getPrice());
			stmt.setString(3, i.getDescription());
			stmt.setInt(4, i.getQuantity());
			stmt.setString(5, i.getPictureUrl());
			stmt.setLong(6, categoryId);
			stmt.setLong(7, i.getCategoryId());

			rs = stmt.getGeneratedKeys();
			rs.next();
			long primaryKey = rs.getLong(1);
			i.setId(primaryKey);
			allItems.put(primaryKey, i);
			synchronized (this) {
				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	

}
