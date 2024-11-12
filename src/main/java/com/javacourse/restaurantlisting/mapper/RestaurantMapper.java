package com.javacourse.restaurantlisting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.javacourse.restaurantlisting.dto.RestaurantDTO;
import com.javacourse.restaurantlisting.entity.Restaurant;


@Mapper
public interface RestaurantMapper {
	
	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
	
	Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
	
	RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);

	
	
}
