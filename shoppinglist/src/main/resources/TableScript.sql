DROP DATABASE if exists shoppinglist;
CREATE DATABASE shoppinglist;

CREATE SCHEMA shoppinglist;
SET search_path TO shoppinglist;


create table Product(
	product_id serial primary key,
	product_name text NOT NULL,
	amount int,
	gram_ml int, 
	priority VARCHAR(20) NOT NULL,
	shopping_location VARCHAR(20) NOT NULL,
	todo Boolean NOT NULL,
	product_created_on Date NOT NULL,
	alarm_date Date
); 


