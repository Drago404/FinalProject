package com.DAO;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.model.Category;

public interface ICategoryDAO {

	
	public Category getCategory(int categoryId);
	
}
