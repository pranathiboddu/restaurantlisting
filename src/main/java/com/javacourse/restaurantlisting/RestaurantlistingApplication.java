package com.javacourse.restaurantlisting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RestaurantlistingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantlistingApplication.class, args);
	}

}
