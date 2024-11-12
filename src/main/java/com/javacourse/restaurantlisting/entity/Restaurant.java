package com.javacourse.restaurantlisting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String address;
	private String city;
	private String restaurantDescription;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRestaurantDescription() {
		return restaurantDescription;
	}
	public void setRestaurantDescription(String restaurantDescription) {
		this.restaurantDescription = restaurantDescription;
	}	
	
	public Restaurant() {
		super();
	}
	
	public Restaurant(Integer id, String name, String address, String city, String restaurantDescription) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.restaurantDescription = restaurantDescription;
	}
	
	
	
	
	
}
