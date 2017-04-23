package com.niit.sanshoppingcartfrontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.sanshopbackend.dao.CategoryDAO;
import com.niit.sanshopbackend.dao.ProductDAO;
import com.niit.sanshopbackend.dao.SupplierDAO;
import com.niit.sanshopbackend.models.Category;
import com.niit.sanshopbackend.models.Product;
import com.niit.sanshopbackend.models.Supplier;


@Controller
public class AdminController {

	@Autowired
	Category category;

	@Autowired
	CategoryDAO categoryDAO;



	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;



	@Autowired
	Supplier supplier;
	@Autowired
	SupplierDAO supplierDAO;

	@RequestMapping(value="/manage_category", method = RequestMethod.GET)

	public ModelAndView manageCategories(Model model) {

		ModelAndView mv = new ModelAndView("/Home");
		List<Category> categoryList = categoryDAO.list();
		
		
		//ModelAndView mv = new ModelAndView("/Admin/AdminHome");
		//mv.addObject("isUserClickedCategory", true);
		//mv.addObject("categoryList", categoryList);
		//mv.addObject("category", category);
		//return mv;
	
		mv.addObject("isAdminClickedCategories", "true");
		//model.addAttribute("isUserClickedCategory", true);
		//model.addAttribute("category", category);
		mv.addObject("categoryList", categoryList);
		
		return mv;
	}
	@RequestMapping(value="/manage_product",method = RequestMethod.GET)
	public String manageProduct(Model model) {
		List<Product> productList = productDAO.list();
		//ModelAndView mv = new ModelAndView("/Admin/AdminHome")type name = new type();
		
		//model.addAttribute("isUserClickedProduct", true);
		model.addAttribute("isAdminClickedProduct", "true");

		model.addAttribute("productList", productList);
		//model.addAttribute("product", product);
		model.addAttribute("supplierList", supplierDAO.list());
		model.addAttribute("supplier",supplier);
		model.addAttribute("categoryList", categoryDAO.list());
		model.addAttribute("category",category);
		
		return "/Home";

	}
	
	@RequestMapping("/manage_supplier")
	public String manageSupplier(Model model) {
		List<Supplier> supplierList = supplierDAO.list();

		model.addAttribute("isAdminClickedSupplier", "true");

		//model.addAttribute("isUserClickedSupplier", true);
		model.addAttribute("supplierList", supplierList);
		//model.addAttribute("supplier", supplier);

		return "/Home";
	}
	
	public List<Category> getCategories_id(){
		List<Category> categoryLists = categoryDAO.list();
		return categoryLists;
		
	}
}
