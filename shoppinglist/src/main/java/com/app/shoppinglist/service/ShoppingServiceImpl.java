package com.app.shoppinglist.service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
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
		Product prod = new Product(product.getProductId(), product.getProductName(), product.getAmount(), 
				product.getGramMl(), product.getPriority(), product.getShoppingLocation(), true, 
				LocalDateTime.now() , product.getAlarmDate()); 
		
		productRepository.save(prod); 
		return prod.getProductId(); 
	}
	
	@Override
	public ProductDTO findProduct(Integer productId) throws ShoppingListException
	{
		System.out.println("in product");
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
		if(productList.isEmpty()) throw new ShoppingListException("Service.NO.PRODUCTS.FOUND.IN.PRIORITY"); 
		
		List<ProductDTO> productDTOList = new ArrayList(); 
		
		for(Product one: productList) {
			
			ProductDTO prodDTO = new ProductDTO(); 
			prodDTO.setAlarmDate(one.getAlarmDate());
			prodDTO.setAmount(one.getAmount());
			prodDTO.setGramMl(one.getGramMl());
			prodDTO.setPriority(one.getPriority());
			prodDTO.setProductCreatedOn(one.getProductCreatedOn());
			prodDTO.setProductId(one.getProductId());
			prodDTO.setProductName(one.getProductName());
			prodDTO.setShoppingLocation(one.getShoppingLocation());
			prodDTO.setTodo(one.getTodo());
			productDTOList.add(prodDTO); 
		}
		return productDTOList; 
	}
	@Override
	public List<ProductDTO> findByShoppingLocation(ShoppingLocation shoppingLocation) throws ShoppingListException
	{
		System.out.println(shoppingLocation);
		System.out.println();
		ShoppingLocation loc = shoppingLocation;
		System.out.println(loc);
		System.out.println(loc.getClass());
		List<Product> productList = productRepository.findByShoppingLocation(shoppingLocation); 
		if(productList.isEmpty()) throw new ShoppingListException("Service.NO.PRODUCTS.FOUND.IN.SHOPPIINGLOCATION"); 
		
		List<ProductDTO> productDTOList = new ArrayList(); 
		
		for(Product one: productList) {
			ProductDTO prodDTO = new ProductDTO(); 
			prodDTO.setAlarmDate(one.getAlarmDate());
			prodDTO.setAmount(one.getAmount());
			prodDTO.setGramMl(one.getGramMl());
			prodDTO.setPriority(one.getPriority());
			prodDTO.setProductCreatedOn(one.getProductCreatedOn());
			prodDTO.setProductId(one.getProductId());
			prodDTO.setProductName(one.getProductName());
			prodDTO.setShoppingLocation(one.getShoppingLocation());
			prodDTO.setTodo(one.getTodo());
			productDTOList.add(prodDTO); 
		}
		return productDTOList; 
	}
	@Override
	public void updateProduct(Integer productId, ProductDTO productDTO) throws ShoppingListException
	{
		Optional<Product> optional = productRepository.findById(productId); 
		Product product = optional.orElseThrow(() -> new ShoppingListException("Service.PRODUCT.NOT.FOUND")); 
		System.out.println("land i here");
		product.setAlarmDate(productDTO.getAlarmDate());
		product.setAmount(productDTO.getAmount());
		product.setGramMl(productDTO.getGramMl());
		product.setPriority(productDTO.getPriority());
		product.setProductCreatedOn(productDTO.getProductCreatedOn());
		product.setProductName(productDTO.getProductName());
		product.setShoppingLocation(productDTO.getShoppingLocation());
		
		
	}
	
	
	@Override
	public void deleteProduct(Integer prodId) throws ShoppingListException
	{
		Optional<Product> optional = productRepository.findById(prodId); 
		Product product = optional.orElseThrow(()-> new ShoppingListException("Service.PRODUCT.NOT.FOUND"));
		
		productRepository.deleteById(prodId);
		
	}

}
