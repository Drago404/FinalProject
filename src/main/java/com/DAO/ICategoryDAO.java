package com.DAO;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.model.Category;
import com.model.Item;

public interface ICategoryDAO {

	
	public Category getCategory(int categoryId);

	public Map<String, Integer> getAllCategories();
	
}
