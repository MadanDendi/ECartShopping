package com.cart.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecart.model.Cartitems;
import com.ecart.model.LoginDetails;
import com.ecart.model.OrderDetails;
import com.ecart.model.OrderDetailsId;
import com.ecart.service.EcartService;
import com.ecart.service.ShoppingCart;

@Controller
public class HelloController {
	
	HttpSession	hs;
	
	@Autowired
	private EcartService eCartService;
	
    @RequestMapping(value="/hello")
    public ModelAndView helloWorld(Cartitems cartpojo,HttpServletRequest request) {
        
//        return new ModelAndView("hello", "setDetailsList",eCartService.getAllCartList());
        return new ModelAndView("/cartStore.jsp?page=hello", "setDetailsList",eCartService.getAllCartList());
    }
    
   @RequestMapping(value="/itemcatalog")
    public ModelAndView addCartItem(Cartitems cartpojo,HttpServletRequest request){
    	hs = request.getSession();
    	ShoppingCart cart = (ShoppingCart) hs.getAttribute("Cart");
    	
    	if(cart==null){
    		cart = new ShoppingCart();
    		hs.setAttribute("Cart", cart);
    	}
    	
    	String servletName = request.getServletPath();
    	
		if (servletName.equals("/itemcatalog.html")) {

			String itemID = request.getParameter("Add");
			Cartitems sets = null;
			
				// Returns the Set Details based on the Set ID

				if (!(itemID == null)) {
					// If the set ID is not null then add the Set Details to the Shopping cart
					sets = eCartService.getCartItemById(itemID);
					cart.add(itemID, sets);
				}
		}
		cart.getTotal();
		return new ModelAndView("/cartStore.jsp?page=hello", "setDetailsList",eCartService.getAllCartList());
    	
    	
    }
   
   @RequestMapping(value="/showcart")
   public ModelAndView showCart(Cartitems cartpojo,HttpServletRequest request){
	   ShoppingCart cart = (ShoppingCart) hs.getAttribute("Cart");
	   String removeParam = request.getParameter("Remove");

		if (removeParam != null && cart!=null && removeParam !="0") {
			// Removes the items from the Shopping Cart
			cart.remove(removeParam);
		}
	   return new ModelAndView("/cartStore.jsp?page=showcart");
   }
   
   @RequestMapping(value="/userLogin")
   public ModelAndView userLogin(Cartitems cartpojo,HttpServletRequest request){
		   return new ModelAndView("/cartStore.jsp?page=userLogin");
   }
   
   
   
   @RequestMapping(value="/loginSubmit")
   public ModelAndView validateLogin(Cartitems cartpojo,HttpServletRequest request){
	   String UserName = request.getParameter("username").toString();
	   String password = request.getParameter("password").toString();
	   String fullName="";
	   
	   LoginDetails returnedDetails = eCartService.validateLogin(UserName, password);
	   if(returnedDetails!=null && returnedDetails.getFullName()!=null && !returnedDetails.getFullName().equals("")){
		   fullName = returnedDetails.getFullName();
		   hs.setAttribute("userID", fullName);
		   return new ModelAndView("/cartStore.jsp?page=checkout","cartHistory",eCartService.getCartHistoryOfUser(UserName));
	   }else{
		   fullName = "Invalid Credentials";
		   return new ModelAndView("/cartStore.jsp?page=userLogin","message",fullName);
	   }
   
	   
   }
   
   @RequestMapping(value="/userRegister")
   public ModelAndView userRegister(Cartitems cartpojo,HttpServletRequest request){
		   return new ModelAndView("/cartStore.jsp?page=userRegister");
   }
   
   @RequestMapping(value="/register")
   public ModelAndView updateUser(Cartitems cartpojo,HttpServletRequest request){
	   String UserName = request.getParameter("username").toString();
	   String password = request.getParameter("password").toString();
	   String fname = request.getParameter("fname").toString();
	   String phone = request.getParameter("phone");
	   if(!StringUtils.isNumeric(phone)  || UserName.isEmpty() || password.isEmpty() || fname.isEmpty()){
		   return new ModelAndView("/cartStore.jsp?page=userRegister","Message","Enter Valid Details");
	   }
	   if(!eCartService.registerUser(UserName, password,fname,phone))
		   return new ModelAndView("/cartStore.jsp?page=userRegister","Message","Selected User Name matches our records please select unique id");
	   hs.setAttribute("userID", fname);
	   
	   return new ModelAndView("/cartStore.jsp?page=checkout","cartHistory",eCartService.getCartHistoryOfUser(UserName));
   }
   
   @RequestMapping(value="/orderConfirmation")
   public ModelAndView completeOrder(Cartitems cartpojo,HttpServletRequest request){
	   hs = request.getSession();
	   Cartitems cartitem = new Cartitems();
	   List<OrderDetailsId> orderlist = new ArrayList<OrderDetailsId>();
	   ShoppingCart cart = (ShoppingCart) hs.getAttribute("Cart");
	   String userId = (String) hs.getAttribute("userID");
	   if(cart!=null){
		   for(int i=0;i<cart.getItems().size();i++){
			   OrderDetailsId order = new OrderDetailsId();
			   cartitem  =  (Cartitems) cart.getItems().get(i).getCartItems();
			   order.setItemId(cartitem.getItemId());
			   order.setUserId(userId);
			   order.setItemPrice(cartitem.getItemPrice());
			   order.setQuantity(cart.getItems().get(i).getCartQuantity());
			   orderlist.add(order);
		   }
		   eCartService.saveOrder(orderlist);
	   }
	   
	   return new ModelAndView("/cartStore.jsp?page=orderConfirmation");
   }
    
}
