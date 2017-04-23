package com.niit.sanshoppingcartfrontend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.PostPersist;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.niit.sanshopbackend.dao.CategoryDAO;
import com.niit.sanshopbackend.dao.ProductDAO;
import com.niit.sanshopbackend.dao.SupplierDAO;
import com.niit.sanshopbackend.dao.UserDAO;
import com.niit.sanshopbackend.models.Category;
import com.niit.sanshopbackend.models.Product;
import com.niit.sanshopbackend.models.Supplier;
import com.niit.sanshopbackend.models.User;
@Controller
public class ProductController {

	@Autowired
	private Product product;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	Supplier supplier;
	@Autowired
	SupplierDAO supplierDAO;
	
	@Autowired
	Category category;

	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping(value="/manage_product_create",method = RequestMethod.POST)
	public String createProductManagement(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("price") Double price,
			@RequestParam("category") String category_id,@RequestParam("supplier") String supplier_id,
			@RequestParam("description") String description,
			@RequestParam("Image") MultipartFile img, Model model,RedirectAttributes redir ) throws InterruptedException{
		
		
		if(productDAO.getProductById(id) == null) {
		//ModelAndView mv = new ModelAndView("redirect:/manage_product_create");
		product.setId(id);
		product.setName(name);
		product.setCategory_id(category_id);
		product.setSupplier_id(supplier_id);
		product.setDescription(description);
		product.setPrice(price);
		
		product.setImage(img);
		
		
		Path paths=Paths.get("C:\\Users\\HP-LAP\\workspace\\ShoppingCartFrontEnd\\src\\main\\webapp\\resources\\productimg\\"+product.getId()+".png");
		try 
		{
		img.transferTo(new File(paths.toString()));
		Thread.sleep(5000);
		//return "redirect:/getAllFlowers";
		} 
		catch (IllegalStateException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		productDAO.save(product);
		redir.addFlashAttribute("success","Product Added Successfully");
		redir.addFlashAttribute("createProduct","true");
		}
		else {
			//model.addAttribute("error","This Product ID is already exist");
			redir.addFlashAttribute("error","Product ID is already exist");
		}
		return "redirect:/manage_product";
	}
	
	@RequestMapping("/ang")
	public ModelAndView showProducts() {
		ModelAndView mv = new ModelAndView("/AngularJs");
		//mv.addObject("isUserClickedRegister", "true");
		return mv;
	}
	
	@RequestMapping(value="/manage_product_edit",method = RequestMethod.GET)
	public String manageProductEdit(Model model) {
		List<Product> productList = productDAO.list();
		//ModelAndView mv = new ModelAndView("/Admin/AdminHome")type name = new type();
		
		model.addAttribute("isUserClickedProduct", true);
		model.addAttribute("isAdminClickedProduct", "true");
        model.addAttribute("createProduct","true");
		model.addAttribute("productList", productList);
		model.addAttribute("product", product);
		model.addAttribute("supplierList", supplierDAO.list());
		model.addAttribute("supplier",supplier);
		model.addAttribute("categoryList", categoryDAO.list());
		model.addAttribute("category",category);

		return "/Home";

	}
	
	
	@GetMapping("/delete_product/{id}")
	public String deleteCategory(@PathVariable("id") String id,Model model,RedirectAttributes redir)
	{
		
		
		Product products = productDAO.getProductById(id);
		if(productDAO.delete(products))
		{
			redir.addFlashAttribute("message", "Successfully delete the product");
			
		}
		else
		{
			redir.addFlashAttribute("message", "Note able delete the product please contact administrator");
			
		}
		
		return "redirect:/manage_product";
		
		
	}
	
	@RequestMapping(value="/manage_product_edit/{id}",method = RequestMethod.GET)
	public String editCategory(@PathVariable("id") String id, Model model){
	
		
		
		 product = productDAO.getProductById(id);
			//List<Category> categoryList = categoryDAO.list();
		

		return "redirect:/manage_product_edit";
		
	}
	@RequestMapping(value="/manage_product_update")
	public String updateCategory(@RequestParam("name") String name,@RequestParam("price") Double price,
			@RequestParam("category") String category_id,@RequestParam("supplier") String supplier_id,
			@RequestParam("description") String description, Model model,@RequestParam("Image") MultipartFile img) throws InterruptedException{
		//category.setId(id);
		
        product.setImage(img);
		
		
		Path paths=Paths.get("C:\\Users\\HP-LAP\\workspace\\ShoppingCartFrontEnd\\src\\main\\webapp\\resources\\productimg\\"+product.getId()+".png");
		try 
		{
		img.transferTo(new File(paths.toString()));
		Thread.sleep(5000);
		//return "redirect:/getAllFlowers";
		} 
		catch (IllegalStateException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		product.setName(name);
		product.setCategory_id(category_id);
		product.setSupplier_id(supplier_id);
		product.setPrice(price);
		product.setDescription(description);
		productDAO.update(product);
		
		return "redirect:/manage_product";
	}
	@RequestMapping(value="/display_product/get/{id}",method=RequestMethod.GET)
	public String showProductById(@PathVariable("id") String id, Model model){
		
		
       
		product = productDAO.getProductById(id);
		model.addAttribute("product",product);
	
		return "redirect:/show_product";
		
	}
	
	List<Product> plist=null;
	@RequestMapping("/productsangular")
	public @ResponseBody List<Product> getValues()
	{	
		plist=productDAO.list();
		List<String> pname=new ArrayList<String>();
		for(Product p:plist)
		{
			pname.add(p.getName());
			
			
		}
		//Gson gson=new Gson();
		//Object result= gson.toJson(pname);
		return plist;
		//return "login";
		 
		}
	
	Set<Product> prod = null;
	String cat_name = null;
	@RequestMapping(value="/category/{id}",method=RequestMethod.GET)
	public ModelAndView  getProduct(@PathVariable("id") String id){
		
		category = categoryDAO.getCategoryById(id);
		 cat_name = category.getName();
		ModelAndView mv  = new ModelAndView("redirect:/categoryproducts");
		
		prod = category.getProducts();
		//mv.addObject("categoryName",cat_name);
		mv.addObject("product",prod);
		mv.addObject("product",product);
		
	
		return mv;
		
	}
	@RequestMapping(value="/categoryproducts",method=RequestMethod.GET)
	public ModelAndView ProductListView(){
	    ModelAndView mv = new ModelAndView("Home");
	    mv.addObject("product",prod);
	    mv.addObject("categoryName",cat_name);
		mv.addObject("userCLickedGetproduct",true);
		return mv;
	}
	@RequestMapping(value="/show_product",method=RequestMethod.GET)
	public String showProduct(Model model) {
		 model.addAttribute("userClikedProduct",true);
		
		model.addAttribute("product",product);
		return "/Home";
	}
}
