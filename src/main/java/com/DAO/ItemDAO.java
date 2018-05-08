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
	private static final String SELECT_ALL_CATEGORIES = "select id,name from category";
	private static final String SELECT_ALL_BRANDS = "select id,name from brand";
	private static final String EDIT_PRODUCT = "update items set name=?, price=?, description=?, quantity=? where id=?";
	private static final String UPDATE_QUANTITY = "UPDATE items SET quantity = quantity - ? Where id = ?";
	private static final String UPDATE_PICTURE = "update items set pictureUrl=? where id=?";

	private static final String GET_BRAND_ID = "select id from brand where name=?";
	private static final String GET_CATEGORY_ID = "select id from category where name=?";

	private Connection conn;
	public static Map<Long, Item> allItems;
	private static Map<Long, String> allCategories;
	private static Map<Long, String> allBrands;
	

	

	private ICategoryDAO categoryDAO;

	public ItemDAO() throws SQLException {
		this.conn = DBConnection.getInstance().getConnection();
		allItems = new HashMap<Long, Item>();
		allCategories = new HashMap<Long, String>();
		allBrands = new HashMap<Long, String>();
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
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public void updateItemQuantity(int id, int quantity) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(UPDATE_QUANTITY);
		stmt.setInt(1, quantity);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		stmt.close();
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
			
			stmt = conn.prepareStatement(ADD_PRODUCT);
			stmt.setString(1, i.getName());
			stmt.setFloat(2, i.getPrice());
			stmt.setString(3, i.getDescription());
			stmt.setInt(4, i.getQuantity());
			stmt.setString(5, i.getPictureUrl());
			stmt.setLong(6, i.getCategoryId());
			stmt.setLong(7, i.getBrandId());

			
			synchronized (this) {
				stmt.execute();
			}
			rs = stmt.getGeneratedKeys();
			rs.next();
			long primaryKey = rs.getLong(1);
			i.setId(primaryKey);
			allItems.put(primaryKey, i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
	public HashMap<Long, String> getAllCategories(){
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CATEGORIES);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				allCategories.put(id, name);
			}
			return (HashMap<Long, String>) allCategories;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap<Long, String> getAllBrands(){
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_BRANDS);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				allBrands.put(id, name);
			}
			return (HashMap<Long, String>) allBrands;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public long getIDbyBrandName(String name) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(GET_BRAND_ID);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				long id = rs.getLong("id");
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long getIDbyCategoryName(String name) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(GET_CATEGORY_ID);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				long id = rs.getLong("id");
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	} 

	@Override
	public void editProduct(long id, String name, float price, String description, int quantity){
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(EDIT_PRODUCT);
			
			stmt.setString(1, name);
			stmt.setFloat(2, price);
			stmt.setString(3, description);
			stmt.setInt(4, quantity);
			stmt.setLong(5, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updatePicture(long id, String picture) {
		
		try {
			PreparedStatement stmt = conn.prepareStatement(UPDATE_PICTURE);
			stmt.setString(1, picture);
			stmt.setLong(2, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public int getItemQuantity(int itemId) {
		// TODO Auto-generated method stub
		return 0;
	}





	

}
