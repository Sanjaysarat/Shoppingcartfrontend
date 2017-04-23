package com.niit.sanshoppingcartfrontend.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.*;

import com.niit.sanshopbackend.dao.CategoryDAO;
import com.niit.sanshopbackend.dao.ProductDAO;
import com.niit.sanshopbackend.dao.SupplierDAO;
import com.niit.sanshopbackend.dao.UserDAO;
import com.niit.sanshopbackend.models.Category;
import com.niit.sanshopbackend.models.Product;
import com.niit.sanshopbackend.models.Supplier;
import com.niit.sanshopbackend.models.User;

@Controller
public class UserController {
	
	@Autowired
	private User user;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private HttpSession session;
	
	



	@RequestMapping("/user_registration")
	public String userRegistration(@RequestParam("user") String id,@RequestParam("name") String name,
			@RequestParam("contactinfo") String contact,@RequestParam("email") String mail,@RequestParam("password") String password,
			RedirectAttributes redir){
		if(userDAO.getUser(id) == null) {
			try{
		user.setId(id);
		user.setName(name);
		user.setContact(contact);
		user.setMail(mail);
		user.setPassword(password);
		user.setRole("ROLE_CUSTOMER");
		userDAO.save(user);
		
		redir.addFlashAttribute("msg","REGISTRATION SUCCESS, PLEASE LOGIN HERE");
		return "redirect:/login";
			}
			catch(Exception e){
				redir.addFlashAttribute("errorMessage","PASSWORD LENGTH MUST BE IN BETWEEN 2-14");
				return "redirect:/home";
			}
		}
		else {
			redir.addFlashAttribute("errorMessage","ID IS NOT AVAILABLE");	
			return "redirect:/home";
		}
		
		
	}
	
	//authentication-failure-forward-url="/loginError"
		@RequestMapping(value = "/loginError", method = RequestMethod.GET)
		public String loginError(Model model) {
			
		
			//log.debug("Starting of the method loginError");
			model.addAttribute("errorMessage", "INVALID CREDENTIALS PLEASE TRY AGAIN.");
			//log.debug("Ending of the method loginError");
			return "Home";

		}
	//<security:access-denied-handler error-page="/accessDenied" />
		@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
		public String accessDenied(Model model,RedirectAttributes redir) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String id = auth.getName();
			
			//log.debug("Starting of the method accessDenied");
			redir.addFlashAttribute("errorMessage", "YOU ARE NOT AUTHORIZED TO ACCESS THIS PAGE ");
			//log.debug("Ending of the method accessDenied");
			return "Home";

		}
		
		
		

}
