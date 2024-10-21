package com.app.shoppinglist.entity;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

import com.app.shoppinglist.dto.ProductPriority;
import com.app.shoppinglist.dto.ShoppingLocation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "product", schema = "shoppinglist")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId; 
	private String productName;
	private Integer amount; 
	private Integer gramMl; 
    @Enumerated(EnumType.STRING)
	private ProductPriority priority;
    @Enumerated(EnumType.STRING)
	private ShoppingLocation shoppingLocation;
	private Boolean todo;
	private LocalDateTime productCreatedOn;
	private LocalDateTime alarmDate;
	
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Integer productId, String productName, Integer amount, Integer gramMl, ProductPriority priority,
			ShoppingLocation shoppingLocation, Boolean todo, LocalDateTime productCreatedOn, LocalDateTime alarmDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.amount = amount;
		this.gramMl = gramMl;
		this.priority = priority;
		this.shoppingLocation = shoppingLocation;
		this.todo = todo;
		this.productCreatedOn = productCreatedOn;
		this.alarmDate = alarmDate;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getGramMl() {
		return gramMl;
	}
	public void setGramMl(Integer gramMl) {
		this.gramMl = gramMl;
	}
	public ProductPriority getPriority() {
		return priority;
	}
	public void setPriority(ProductPriority priority) {
		this.priority = priority;
	}
	public ShoppingLocation getShoppingLocation() {
		return shoppingLocation;
	}
	public void setShoppingLocation(ShoppingLocation shoppingLocation) {
		this.shoppingLocation = shoppingLocation;
	}
	public Boolean getTodo() {
		return todo;
	}
	public void setTodo(Boolean todo) {
		this.todo = todo;
	}
	public LocalDateTime getProductCreatedOn() {
		return productCreatedOn;
	}
	public void setProductCreatedOn(LocalDateTime localDateTime) {
		this.productCreatedOn = localDateTime;
	}
	public LocalDateTime getAlarmDate() {
		return alarmDate;
	}
	public void setAlarmDate(LocalDateTime localDateTime) {
		this.alarmDate = localDateTime;
	}
	@Override
	public int hashCode() {
		return Objects.hash(productId, productName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(productName, other.productName);
	}

	
	
	
}
