package com.ecart.model.DaoIMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ecart.model.Cartitems;
import com.ecart.model.OrderDetails;
import com.ecart.model.OrderDetailsId;
import com.ecart.model.dao.SaveOrderDAO;

public class SaveOrderDAOIMPL extends HibernateDaoSupport implements SaveOrderDAO{

	@Override
	public void saveOrder(List<OrderDetailsId> order) {
		OrderDetails orderdetail = new OrderDetails();
		for(int i = 0;i<order.size();i++){
			 orderdetail.setId(order.get(i));
			 getHibernateTemplate().save(orderdetail);
		}
	}

	@Override
	public List<Cartitems> getCartHistoryOfUser(String uid) {
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select itemName from Cartitems where itemId in (select id.itemId from OrderDetails where id.userId = ?)",uid);
		List<Cartitems> cartHistory = new ArrayList<Cartitems>();
		Cartitems ctItem = new Cartitems();
			ctItem.setItemName(list.toString());
			cartHistory.add(ctItem);
			
		return cartHistory;
	}

}
