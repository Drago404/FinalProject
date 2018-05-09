package com.model;

import com.exceptions.ItemException;

public class Item {

	private long id;
	private String name;
	private float price;
	private String description;
	private int quantity;
	private String pictureUrl;
	private long categoryId;
	private long brandId;
	

	public Item(int id,String name, float price, String description, int quantity, String pictureUrl, long categoryId,long brandId) {
		setId(id);
		setName(name);
		setPrice(price);
		setDescription(description);
		setQuantity(quantity);
		setPictureUrl(pictureUrl);
		setCategoryId(categoryId);
		setBrandId(brandId);
	}
	
	public Item(String name, long brandId, float price, int quantity, long categoryId, String description, String pictureUrl) {
		setName(name);
		setBrandId(brandId);
		setPrice(price);
		setQuantity(quantity);
		setCategoryId(categoryId);
		setDescription(description);
		setPictureUrl(pictureUrl);
	}

	public Item() {

	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		if (name != null && name.trim().length() > 0) {
			this.name = name;
		} else
			new ItemException("invalid item name");
	}

	public void setPrice(float price) {
		if(price > 0) {
		this.price = price;
		} 
		else new ItemException("invalid price");
	}

	public void setDescription(String description) {
		if (description != null && description.trim().length() > 0) {
			this.description = description;
		} else
			new ItemException("invalid item description");
	}

	public void setQuantity(int quantity) {
		if (quantity >= 0) {
			this.quantity = quantity;
		} else
			new ItemException("invalid quantity");
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (brandId ^ (brandId >>> 32));
		result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pictureUrl == null) ? 0 : pictureUrl.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (brandId != other.brandId)
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pictureUrl == null) {
			if (other.pictureUrl != null)
				return false;
		} else if (!pictureUrl.equals(other.pictureUrl))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
	

//	public void setPictureUrl(String pictureUrl) {
//		if(pictureUrl.matches("http(s?)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./]*)+\\.(?:[gG][iI][fF]|[jJ][pP][gG]|[jJ][pP][eE][gG]|[pP][nN][gG]|[bB][mM][pP])")) {
//		this.pictureUrl = pictureUrl;
//	}
//		new ItemException("invalid photo URL");
//	}

}
