package com.DAO;

import java.sql.SQLException;
import java.util.List;

import com.model.Category;
import com.model.Item;

public interface IitemDAO {
	
	public List<Item> getItemsByCategory(Category category) throws SQLException;

	public Item getItem(int id) throws SQLException;

	public List<Item> getItemsByName(String text);

	public void updateItemQuantity(int id, int quantity) throws SQLException;

	void editProduct(long id, String name, float price, String description, int quantity);

	void updatePicture(long id, String picture);

	long getIDbyBrandName(String name);

	long getIDbyCategoryName(String name);

	List<Item> getItemsByCategoryAndBrand(Category category, int brandId) throws SQLException;

	
}
