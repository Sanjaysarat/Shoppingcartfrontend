package com.niit.sanshoppingcartfrontend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.sanshopbackend.dao.CartDAO;
import com.niit.sanshopbackend.dao.CategoryDAO;
import com.niit.sanshopbackend.dao.ProductDAO;
import com.niit.sanshopbackend.dao.UserDAO;
import com.niit.sanshopbackend.models.MyCart;
import com.niit.sanshopbackend.models.Product;
import com.niit.sanshopbackend.models.User;
import com.sun.javafx.sg.prism.NGShape.Mode;

@Controller
public class CartController {

	private static Logger log = LoggerFactory.getLogger(CartController.class);
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private MyCart myCart;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private HttpSession session;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/cart/add/{id}", method = RequestMethod.GET)
	public String saveCart(@PathVariable("id") String id, Model model,RedirectAttributes redir) {
		log.debug("Starting method of saveCart");
try {
		User user = (User) session.getAttribute("user");
		// System.out.println(user.getId());
		String user_id = user.getId();
		Product product = productDAO.getProductById(id);
		String product_name = product.getName();
		Double price = product.getPrice();
       
		Long d = System.currentTimeMillis();
		Date today = new Date(d); 
		myCart.setUser_id(user_id);
		myCart.setProduct_name(product_name);
		myCart.setPrice(price);
		myCart.setQuantity(1);
		myCart.setStatus('N');
		myCart.setDate_added(today);
		cartDAO.save(myCart);
		log.debug("Ending method saveCart");
		//model.addAttribute("cartMsg", "Item added successfully");
		redir.addFlashAttribute("msg","ITEM ADDED SUCCESSFULLY");
		return "redirect:/myCart";
}
catch(Exception e) {
	redir.addFlashAttribute("errorMessage","PLEASE LOGIN TO ADD PRODUCT INTO CART");
	return "redirect:/home";
}
	}

	@RequestMapping(value = "/myCart")
	public ModelAndView showCart() {
		log.debug("Starting method show cart");

		ModelAndView mv = new ModelAndView("Home");
		User user = (User) session.getAttribute("user");
		String user_id = user.getId();
		
		List cart_list = (List) cartDAO.list(user_id);
		int count = cart_list.size();
		session.setAttribute("cartSize",count);
		Double total_amount = cartDAO.getTotalAmount(user_id);
		// mv.addObject("myCart",cartDAO.get(user_id));
		mv.addObject("cart", cart_list);

		mv.addObject("TotalAmount", total_amount);
		mv.addObject("userClickedCart", true);
		log.debug("Ending method show cart");
		return mv;
	}

	@RequestMapping(value = "/cart")
	public String ViewCart(Model model) {

		model.addAttribute("userClickedCart", true);
		return "Home";
	}
	
	@RequestMapping("/delete_cart/{id}")
	public ModelAndView deleteCart(@PathVariable("id") String id){
		
		ModelAndView mv = new ModelAndView("redirect:/myCart");
		myCart = cartDAO.get(id);
		cartDAO.delete(myCart);
		mv.addObject("cartDelete","Item deleted");
		return mv;
		
		
	}
	@RequestMapping("/cart_checkout")
	public String cart_Checkout(Model model){
		
		
		User user = (User) session.getAttribute("user");
		String user_id = user.getId();
		
		List cart_list = (List) cartDAO.list(user_id);
		int count = cart_list.size();
		session.setAttribute("cartSize",count);
		Double total_amount = cartDAO.getTotalAmount(user_id);
		// mv.addObject("myCart",cartDAO.get(user_id));
		model.addAttribute("cart", cart_list);

		model.addAttribute("TotalAmount", total_amount);
		
		
		
		
		model.addAttribute("userClickedCheckout",true);
		return "Home";
		
	}
	@RequestMapping("/checkout_complete")
	public String checkoutComplete(Model model){
		
		User user = (User) session.getAttribute("user");
		String user_id = user.getId();
		
		List<MyCart> cart_list =  cartDAO.list(user_id);
		
		for(MyCart p:cart_list){
	System.out.println("Cart ID is"+p.getId());
	
	
	myCart.setId(p.getId());
	myCart.setStatus('C');
	myCart.setDate_added(p.getDate_added());
	myCart.setPrice(p.getPrice());
	
	myCart.setProduct_name(p.getProduct_name());
	myCart.setQuantity(p.getQuantity());
	myCart.setUser_id(user_id);
	cartDAO.update(myCart);
	session.setAttribute("cartSize",0);
			
		}
		model.addAttribute("userclickedPlaced",true);
		return "Home";
		
	}
}
