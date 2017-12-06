package com.ecart.model.DaoIMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ecart.model.Cartitems;

import com.ecart.model.dao.CartItemsDao;

public class CartItemsDaoIMPL extends HibernateDaoSupport implements CartItemsDao {

	private static String name;

	@Override
	public List<Cartitems> getAllCartItems() {
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select itemId,itemName,itemPrice,itemImageUrl from Cartitems");
		List<Cartitems> cartItems = new ArrayList<Cartitems>();
		for(Object[] objs:list){
			Cartitems cItem = new Cartitems();
			cItem.setItemId(Integer.parseInt(objs[0].toString()));
			cItem.setItemName(objs[1].toString());
			cItem.setItemPrice(objs[2].toString());
			cItem.setItemImageUrl(objs[3].toString());
			cartItems.add(cItem);
		}
		
		return cartItems;
	}
	
	@Override
	public Cartitems getItemById(String id) {
		System.out.println("$$$$$$$$$ ID : "+id);
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select itemName,itemPrice,itemId from Cartitems where itemId=?",Integer.parseInt(id));
		Cartitems cti = new Cartitems();
		for(Object[] obj:list){
			cti.setItemName(obj[0].toString());
			cti.setItemPrice((obj[1].toString()));
			cti.setItemId(Integer.parseInt(obj[2].toString()));
		}
		
		return cti;
	}
	
	

	public static void main(String[] args){
		System.out.println("+++++++++++++++++++++++1111111111111");
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-servlet.xml");
		CartItemsDaoIMPL dao =(CartItemsDaoIMPL)context.getBean("metaDao");
		//List<Cartitems> cartlist = (List<Cartitems>) entityManager.createNamedQuery("select * from cartitem");
		System.out.println("+++++++++++++++++++++++222222222222222");
		List<Cartitems> cartlist = dao.getAllCartItems();
		name = "";
		for(int i =0;i<cartlist.size();i++){
			System.out.println(i+"Item Name : "+cartlist.get(i).getItemName());
			System.out.println(i+"Item Price : "+cartlist.get(i).getItemPrice());
		}
		
		
	}


	
}
