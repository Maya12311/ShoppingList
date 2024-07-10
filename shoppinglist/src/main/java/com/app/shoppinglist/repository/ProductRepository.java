package com.app.shoppinglist.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.shoppinglist.dto.ProductPriority;
import com.app.shoppinglist.dto.ShoppingLocation;
import com.app.shoppinglist.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByPriority(ProductPriority priority); 
	List<Product> findByShoppingLocation(ShoppingLocation shoppingLocation);
	List<Product> findByTodo(Boolean todo); 
}
