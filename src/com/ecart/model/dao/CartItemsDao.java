package com.ecart.model.dao;

import java.util.List;

import com.ecart.model.Cartitems;



public interface CartItemsDao {
	
	public List<Cartitems> getAllCartItems();
	public Cartitems getItemById(String id);
	

}
