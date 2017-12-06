package com.ecart.service;

import java.util.List;

import com.ecart.model.Cartitems;
import com.ecart.model.LoginDetails;
import com.ecart.model.OrderDetailsId;
import com.ecart.model.dao.CartItemsDao;
import com.ecart.model.dao.LoginDao;
import com.ecart.model.dao.SaveOrderDAO;

public class EcartService {
	
	private CartItemsDao cartdao = null;
	private LoginDao logindao = null;
	private SaveOrderDAO saveorderdao = null;

	public CartItemsDao getCartdao() {
		return cartdao;
	}

	public void setCartdao(CartItemsDao cartdao) {
		this.cartdao = cartdao;
	}
	
	public SaveOrderDAO getSaveorderdao() {
		return saveorderdao;
	}

	public void setSaveorderdao(SaveOrderDAO saveorderdao) {
		this.saveorderdao = saveorderdao;
	}

	public LoginDao getLogindao() {
		return logindao;
	}

	public void setLogindao(LoginDao logindao) {
		this.logindao = logindao;
	}

	public List<Cartitems> getAllCartList(){
		List<Cartitems> cartList = cartdao.getAllCartItems();
		return cartList;
	}
	
	public Cartitems getCartItemById(String Id){
		Cartitems item  = cartdao.getItemById(Id);		
		return item;
	}
	
	public LoginDetails validateLogin(String userId,String password){
		LoginDetails loggedDetails = logindao.validateLogin(userId, password);
		return loggedDetails;
	}
	
	public Boolean registerUser(String uid,String pwd,String fname,String phn){
		boolean updated = logindao.registerUser(uid,pwd,fname,phn);
		return updated;
	}
	
	public void saveOrder(List<OrderDetailsId> order){
		saveorderdao.saveOrder(order);
	}
	
	public List<Cartitems> getCartHistoryOfUser(String uid){
		return saveorderdao.getCartHistoryOfUser(uid);
	}

}
