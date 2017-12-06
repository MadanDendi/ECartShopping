package com.ecart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ecart.model.Cartitems;


public class ShoppingCart {

	private HashMap<String, ShoppingCartItem>	items = null;
	int numberOfItems	= 0;
	double total = 0.0;

	public ShoppingCart() {
		items = new HashMap<String, ShoppingCartItem>();
	}

	public synchronized void add(String itemID, Cartitems setItems) {

		if (items.containsKey(itemID)) {
			ShoppingCartItem scitem = (ShoppingCartItem) items.get(itemID);
			scitem.incrementQuantity();
		} else {
			ShoppingCartItem newItem = new ShoppingCartItem(setItems);
			items.put(itemID, newItem);
		}
	}

	public synchronized List<ShoppingCartItem> getItems() {
		List<ShoppingCartItem> results = new ArrayList<ShoppingCartItem>();
		results.addAll(this.items.values());

		return results;
	}

	public synchronized int getNumberOfItems() {
		numberOfItems = 0;

		for (Iterator<ShoppingCartItem> i = getItems().iterator(); i.hasNext();) {
			ShoppingCartItem item = i.next();
			numberOfItems += item.getCartQuantity();
			//System.out.println("number of items is " + numberOfItems);
		}

		return numberOfItems;
	}

	public synchronized void remove(String itemID) {
		if (items.containsKey(itemID)) {
			ShoppingCartItem scitem = (ShoppingCartItem) items.get(itemID);
			scitem.decrementQuantity();

			if (scitem.getCartQuantity() <= 0) {
				items.remove(itemID);
			}

			numberOfItems--;
		}
	}

	public synchronized void clear() {
		System.err.println("Clearing cart.");
		items.clear();
		numberOfItems = 0;
	}

	public synchronized double getTotal() {
		
		double amount = 0.0;
		for(Iterator<ShoppingCartItem> i = getItems().iterator();i.hasNext();) {
			ShoppingCartItem item = (ShoppingCartItem)i.next();
			Cartitems s = (Cartitems)item.getCartItems();
			int itemPrice = Integer.parseInt(s.getItemPrice().substring(0, s.getItemPrice().indexOf('.')));
			System.out.println("Item Priceccccccccccc "+itemPrice);			
			amount += item.getCartQuantity() * itemPrice;
		}
		System.out.println("Total Amount"+amount);
		total=amount;
		return amount;

	}
}

