package com.niit.sanshoppingcartfrontend.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.sanshopbackend.dao.CartDAO;
import com.niit.sanshopbackend.dao.CategoryDAO;
import com.niit.sanshopbackend.dao.ProductDAO;
import com.niit.sanshopbackend.dao.SupplierDAO;
import com.niit.sanshopbackend.dao.UserDAO;
import com.niit.sanshopbackend.models.Category;
import com.niit.sanshopbackend.models.Product;
import com.niit.sanshopbackend.models.Supplier;
import com.niit.sanshopbackend.models.User;
@Controller
public class SpringSecurity {
@Autowired
private HttpSession session;

@Autowired
private CartDAO cartDAO;

@Autowired
private UserDAO userDAO;
	@RequestMapping("user_home")
	public ModelAndView validation(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("/Home");
		System.out.println("validation started");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println(auth.getName());
		String name = auth.getName();
		User user = userDAO.getUser(name);
		session.setAttribute("user", user);
	    session.setAttribute("user_id", name);
		session.setAttribute("userName", name);
		if(request.isUserInRole("ROLE_ADMIN")){
			mv.addObject("isAdmin", "true");
			
			session.setAttribute("userRole", "ROLE_ADMIN");
		}
		else {
			int count = cartDAO.list(name).size();
			session.setAttribute("cartSize",count);
			session.setAttribute("userRole", "customer");
			mv.addObject("isAdmin", "false");
		}
		session.setAttribute("isUserLoggedIn",true);
		return mv;
	}

}
