package com.app.shoppinglist.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class ProductDTO {
	
	
	private Integer productId; 
	private String productName; 
	private Integer amount;
	private Integer gramMl;
	private ProductPriority priority; 
	private ShoppingLocation shoppingLocation; 
	private Boolean todo; 
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSSSS", shape = JsonFormat.Shape.STRING)

	private LocalDateTime productCreatedOn; 
//@JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDateTime alarmDate;
	
	
	
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(Integer productId, String productName, Integer amount, Integer gramMl, ProductPriority priority,
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
	public void setProductCreatedOn(LocalDateTime productCreatedOn) {
		this.productCreatedOn = productCreatedOn;
	}
	public LocalDateTime getAlarmDate() {
		return alarmDate;
	}
	public void setAlarmDate(LocalDateTime alarmDate) {
		this.alarmDate = alarmDate;
	}
	
		

}
