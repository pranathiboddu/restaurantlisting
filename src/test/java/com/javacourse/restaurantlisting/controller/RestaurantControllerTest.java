package com.javacourse.restaurantlisting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javacourse.restaurantlisting.dto.RestaurantDTO;
import com.javacourse.restaurantlisting.service.RestaurantService;

public class RestaurantControllerTest {
	
	
	@InjectMocks
	RestaurantController restaurantController;
	
	@Mock
	RestaurantService restaurantService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	
	@Test
	public void testFetchAllRestaurants() {
		
		//Mock the service Behavior
		
		List<RestaurantDTO> mockRestaurants = Arrays.asList(				
				new RestaurantDTO(1,"Restaurant 1","Address 1","city 1","Description 1"),
				new RestaurantDTO(1,"Restaurant 2","Address 2","city 2","Description 2")
				);
		
		when(restaurantService.fetchAllRestaurants()).thenReturn(mockRestaurants);
				
		//call the controller method
		ResponseEntity<List<RestaurantDTO>> responseEntity = restaurantController.fetchAllRestaurants();
		
		//Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockRestaurants, responseEntity.getBody());
		
		//Verify the service method was called		
		verify(restaurantService,times(1)).fetchAllRestaurants();				
		
	}
	
	@Test
	public void testSaveRestaurant()
	{
		//create test data
		RestaurantDTO mockRestaurant = new RestaurantDTO(1,"Restaurant 1","Address 1","city 1", "Description 1");
		
		when(restaurantService.saveRestaurant(mockRestaurant)).thenReturn(mockRestaurant);
		
		//call the controller method
		ResponseEntity<RestaurantDTO> responseEntity = restaurantController.saveRestaurant(mockRestaurant);
		
		//Verify the response
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(mockRestaurant, responseEntity.getBody());
		

        // Verify that the service method was called
        verify(restaurantService, times(1)).saveRestaurant(mockRestaurant);
	}
	
	@Test
	public void testFindRestaurantById() {
		// Create a mock restaurant ID
        Integer mockRestaurantId = 1;
		
        //create a mock restaurant to be returned by the service
		RestaurantDTO mockRestaurant = new RestaurantDTO(1,"Restuarant 1","Address 1", "city 1" ,"Description 1");
		
		//mock the service behavior
        when(restaurantService.findRestaurantById(mockRestaurantId)).thenReturn(new ResponseEntity<>(mockRestaurant, HttpStatus.OK));
		
		//call the controller method
		ResponseEntity<RestaurantDTO> responseEntity = restaurantController.findRestaurantById(mockRestaurantId);
		
		//verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockRestaurant, responseEntity.getBody());
		
		//verify that the service method was called
		verify(restaurantService,times(1)).findRestaurantById(mockRestaurantId);
		
	}

}
