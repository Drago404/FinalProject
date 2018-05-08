package com.DAO;

import java.sql.SQLException;
import java.util.List;

import com.model.Category;
import com.model.Item;

public interface IitemDAO {
	
	public List<Item> getItemsByCategory(Category category) throws SQLException;

	public Item getItem(int id) throws SQLException;

	public List<Item> getItemsByName(String text);

	public int getItemQuantity(int itemId);

	public void updateItemQuantity(int id, int quantity) throws SQLException;

	
}
