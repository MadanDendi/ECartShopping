package com.ecart.service;

public class ShoppingCartItem {
	Object cartItems;
	int cartQuantity;
	public Object getCartItems() {
		return cartItems;
	}
	public void setCartItems(Object cartItems) {
		this.cartItems = cartItems;
	}
	public int getCartQuantity() {
		return cartQuantity;
	}
	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}
	
	public ShoppingCartItem(Object cartItems) {
		this.cartItems = cartItems;
		cartQuantity = 1;
	}
	
	public void incrementQuantity() {
		cartQuantity++;
	}

	public void decrementQuantity() {
		cartQuantity--;
	}
	


}
