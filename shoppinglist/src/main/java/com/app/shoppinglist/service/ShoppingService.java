package com.app.shoppinglist.service;

import java.util.List;

import com.app.shoppinglist.dto.ProductDTO;
import com.app.shoppinglist.dto.ProductPriority;
import com.app.shoppinglist.dto.ShoppingLocation;
import com.app.shoppinglist.exception.ShoppingListException;

public interface ShoppingService {

	public Integer addProduct(ProductDTO product) throws ShoppingListException; 
	public ProductDTO findProduct(Integer productId) throws ShoppingListException; 
	public List<ProductDTO> findAllProducts()throws ShoppingListException;
	public List<ProductDTO> findByPriority(ProductPriority priority) throws ShoppingListException; 
	public List<ProductDTO> findByShoppingLocation(ShoppingLocation shoppingLocation) throws ShoppingListException; 
	public void updateProduct(ProductDTO product) throws ShoppingListException; 
	public void deleteProduct(Integer productId) throws ShoppingListException; 
}
