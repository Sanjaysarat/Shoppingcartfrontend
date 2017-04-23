package com.niit.sanshoppingcartfrontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.sanshopbackend.dao.CategoryDAO;
import com.niit.sanshopbackend.dao.ProductDAO;
import com.niit.sanshopbackend.dao.SupplierDAO;
import com.niit.sanshopbackend.dao.UserDAO;
import com.niit.sanshopbackend.models.Category;
import com.niit.sanshopbackend.models.Product;
import com.niit.sanshopbackend.models.Supplier;
import com.niit.sanshopbackend.models.User;
import com.sun.javafx.sg.prism.NGShape.Mode;

@Controller
public class HomeController {

	@Autowired
	private HttpSession session;

	@Autowired
	private Category category;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Supplier supplier;
	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Product product;
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private User user;

	@RequestMapping("/home")
	public String reDirectToHome() {
		session.setAttribute("category", category);
		session.setAttribute("supplier", supplier);
		session.setAttribute("product", product);

		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("productList", productDAO.list());
		return "Home";
	}

	@RequestMapping(value={"/"})
	public ModelAndView onLoad() {
		ModelAndView mv = new ModelAndView("/Home");
		
		System.out.println("Game Started");
		session.setAttribute("category", category);
		session.setAttribute("supplier", supplier);
		session.setAttribute("product", product);

		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("productList", productDAO.list());
		return mv;
	}

	String message = "Welcome to Spring MVC!";

	@RequestMapping("/login")
	public ModelAndView showMessage() {

		session.setAttribute("category", category);
		session.setAttribute("supplier", supplier);
		session.setAttribute("product", product);

		session.setAttribute("categoryList", categoryDAO.list());
		session.setAttribute("supplierList", supplierDAO.list());
		session.setAttribute("productList", productDAO.list());
		ModelAndView mv = new ModelAndView("/Home");
		mv.addObject("isUserClickedLogin", "true");
		return mv;
	}

	@RequestMapping("/register")
	public ModelAndView showRegister() {
		ModelAndView mv = new ModelAndView("/Home");
		mv.addObject("isUserClickedRegister", "true");
		return mv;
	}
	

	
	
	
	@RequestMapping("/j_spring_security_logout")
	public String logout(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redir) {
		
		// ModelAndView mv = new ModelAndView("/Home");
		 CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		    cookieClearingLogoutHandler.logout(request, response, null);
		    securityContextLogoutHandler.logout(request, response, null);
		session.removeAttribute("isUserLoggedIn");
		session.removeAttribute("userRole");
		session.removeAttribute("userName");
		session.removeAttribute("user");
		session.removeAttribute("cartSize");
		
		redir.addFlashAttribute("msg","YOU HAVE BEEN LOGGED OUT");
		return "redirect:home";
	}

}
