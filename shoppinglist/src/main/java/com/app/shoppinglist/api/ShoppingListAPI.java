package com.app.shoppinglist.api;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.shoppinglist.dto.ProductDTO;
import com.app.shoppinglist.dto.ProductPriority;
import com.app.shoppinglist.dto.ShoppingLocation;
import com.app.shoppinglist.exception.ShoppingListException;
import com.app.shoppinglist.repository.ProductRepository;
import com.app.shoppinglist.service.ShoppingService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value="/shoppinglist")
public class ShoppingListAPI {
	
	@Autowired
	private ShoppingService shoppingService;
	@Autowired
	private Environment environment;
	
	
	
	@GetMapping(value="/products/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer productId) throws ShoppingListException{
		System.out.println("in product");
		ProductDTO product = shoppingService.findProduct(productId); 
		return new ResponseEntity<>(product, HttpStatus.OK); 
	}

	@GetMapping(value="/products")
public ResponseEntity <List<ProductDTO>>  getAllProducts() throws ShoppingListException{
		List<ProductDTO> products = shoppingService.findAllProducts(); 
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	 @GetMapping("/")
	    public String test(HttpServletRequest request) {
	        return  "Blu "+request.getSession().getId();
	    }
	
	@GetMapping(value="/findByShoppingLocation")
	@ResponseBody
	public ResponseEntity<List<ProductDTO>> findByShoppingLocation(@RequestParam ShoppingLocation shoppingLocation) throws ShoppingListException{
		System.out.println(shoppingLocation);
System.out.println("hey"+ shoppingLocation);

		List<ProductDTO> products = shoppingService.findByShoppingLocation(shoppingLocation); 
		for(ProductDTO loc: products) {
			System.out.println(loc.getProductName());

		}
		return new ResponseEntity<>(products, HttpStatus.OK); 
	}
	
	@GetMapping(value="/findByPriority")
	@ResponseBody
	public ResponseEntity<List<ProductDTO>> findByPriority(@RequestParam ProductPriority priority) throws ShoppingListException{
		System.out.println(priority);
		List<ProductDTO> products = shoppingService.findByPriority(priority); 
		for(ProductDTO prio: products) {
			System.out.println(prio.getProductName());
		}
		
		return new ResponseEntity<>(products, HttpStatus.OK); 
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return  (CsrfToken) request.getAttribute( "_csrf");
	}
	
	@PostMapping(value="/products")
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) throws ShoppingListException{
		System.out.println("in api class");
		System.out.println(productDTO);
		Integer productId = shoppingService.addProduct(productDTO); 
		String successMessage = environment.getProperty("API.INSERT_SUCCESS") + productId; 
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED); 
	}
	
	@PutMapping(value = "/products/{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable Integer productId ,@RequestBody ProductDTO productDTO) throws Exception{
		System.out.println("and here");
		shoppingService.updateProduct(productId, productDTO); 
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS"); 
				return new ResponseEntity<>(successMessage, HttpStatus.OK); 
	}
	
	
	
	@DeleteMapping(value= "/products/{productId}")
	public ResponseEntity<String> deleteProduct (@PathVariable Integer productId) throws Exception{
		
		shoppingService.deleteProduct(productId); 
		String successMessage = environment.getProperty("API.DELETE_SUCCESS"); 
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	
	
	
}
