package com.ecart.model.dao;

import java.util.List;

import com.ecart.model.Cartitems;
import com.ecart.model.OrderDetailsId;

public interface SaveOrderDAO {
	public void saveOrder(List<OrderDetailsId> order);
	public List<Cartitems> getCartHistoryOfUser(String uid);

}
