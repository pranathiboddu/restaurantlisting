package com.javacourse.restaurantlisting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.javacourse.restaurantlisting.dto.RestaurantDTO;
import com.javacourse.restaurantlisting.entity.Restaurant;
import com.javacourse.restaurantlisting.mapper.RestaurantMapper;
import com.javacourse.restaurantlisting.repository.RestaurantRepository;


@Service
public class RestaurantService {
	
	@Autowired
	 RestaurantRepository restaurantRepository;

	@Cacheable(value="restaurant")
	public List<RestaurantDTO> fetchAllRestaurants() 
	{
		List<Restaurant>  restaurants = restaurantRepository.findAll();
		System.out.println("Getting from db");

		//map it to list of DTOs		
		List<RestaurantDTO> restaurantDTOList = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
		return restaurantDTOList;
		
	}

	public RestaurantDTO saveRestaurant(RestaurantDTO restaurantDTO) {
		
		Restaurant savedRestaurant = restaurantRepository.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));		
		return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
	}

	@Cacheable(value="restaurant",key="#id")
	public ResponseEntity<RestaurantDTO> findRestaurantById(Integer id) 
	{
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		System.out.println("Getting from db");
		if(restaurant.isPresent())
		{
			return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()),HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

			
	}
}
