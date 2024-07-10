package com.app.shoppinglist.service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shoppinglist.dto.ProductDTO;
import com.app.shoppinglist.dto.ProductPriority;
import com.app.shoppinglist.dto.ShoppingLocation;
import com.app.shoppinglist.entity.Product;
import com.app.shoppinglist.exception.ShoppingListException;
import com.app.shoppinglist.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service(value = "ShoppingService")
@Transactional
public class ShoppingServiceImpl implements ShoppingService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Integer addProduct(ProductDTO product) throws ShoppingListException
	{
		Optional<Product> optional = productRepository.findById(product.getProductId()); 
		Product prod = optional.orElseThrow(() -> new ShoppingListException("Service.PRODUCT.ALREADY.EXCISTS"));
		
	prod.setProductName(product.getProductName());
	prod.setShoppingLocation(product.getShoppingLocation());
	prod.setAlarmDate(product.getAlarmDate());
	prod.setProductCreatedOn(LocalDateTime.now());
	prod.setAmount(product.getAmount());
	prod.setGramMl(product.getGramMl());
	prod.setPriority(product.getPriority());
	prod.setProductCreatedOn(product.getProductCreatedOn());
	prod.setTodo(true);
	
	Product addedProduct = productRepository.save(prod); 
		return  addedProduct.getProductId(); 
	}
	
	@Override
	public ProductDTO findProduct(Integer productId) throws ShoppingListException
	{
		Optional<Product> optional = productRepository.findById(productId); 
		Product product = optional.orElseThrow(() -> new ShoppingListException("Service.PRODUCT.NOT.FOUND")); 
		ProductDTO productDto = new ProductDTO(); 
		
		productDto.setAlarmDate(product.getAlarmDate());
		productDto.setAmount(product.getAmount());
		productDto.setGramMl(product.getGramMl());
		productDto.setPriority(product.getPriority());
		productDto.setProductCreatedOn(product.getProductCreatedOn());
		productDto.setProductId(product.getProductId());
		productDto.setProductName(product.getProductName());
		productDto.setShoppingLocation(product.getShoppingLocation());
		productDto.setTodo(product.getTodo());
		return productDto;
	}
	@Override
	public List<ProductDTO> findAllProducts() throws ShoppingListException
	{
		List<Product> productList = productRepository.findByTodo(true); 
		if(productList.isEmpty()) throw new ShoppingListException("Service.PRODUCTS.NOT.FOUND"); 
		
		List<ProductDTO> productDtoList = new ArrayList(); 
		for(Product one: productList) {
			ProductDTO prodDto = new ProductDTO(); 
			prodDto.setAlarmDate(one.getAlarmDate());
			prodDto.setAmount(one.getAmount());
			prodDto.setGramMl(one.getGramMl());
			prodDto.setPriority(one.getPriority());
			prodDto.setProductCreatedOn(one.getProductCreatedOn());
			prodDto.setProductId(one.getProductId());
			prodDto.setProductName(one.getProductName());
			prodDto.setShoppingLocation(one.getShoppingLocation());
			prodDto.setTodo(one.getTodo());
			productDtoList.add(prodDto); 
		}
		return productDtoList; 
	}
	@Override
	public List<ProductDTO> findByPriority(ProductPriority priority) throws ShoppingListException
	{
		List<Product> productList = productRepository.findByPriority(priority); 
		if(productList.isEmpty()) throw new ShoppingListException("Service.PRODUCTS.NOT.FOUND"); 
		
		List<ProductDTO> productDTOList = new ArrayList(); 
		for(Product one: productList) {
			
		}
		return
	}
	@Override
	public List<ProductDTO> findByShoppingLocation(ShoppingLocation shoppingLocation) throws ShoppingListException
	{
		return
	}
	@Override
	public void updateProduct() throws ShoppingListException
	{
		
	}
	@Override
	public void deleteProduct() throws ShoppingListException
	{
		
	}

}
