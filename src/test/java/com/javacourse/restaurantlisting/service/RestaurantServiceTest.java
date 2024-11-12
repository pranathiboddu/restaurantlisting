package com.javacourse.restaurantlisting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javacourse.restaurantlisting.dto.RestaurantDTO;
import com.javacourse.restaurantlisting.entity.Restaurant;
import com.javacourse.restaurantlisting.mapper.RestaurantMapper;
import com.javacourse.restaurantlisting.repository.RestaurantRepository;

public class RestaurantServiceTest {
	
	@InjectMocks
	RestaurantService restaurantService;
	
	@Mock
	RestaurantRepository restaurantRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
    public void testFetchAllRestaurants() {
        // Create mock restaurants
        List<Restaurant> mockRestaurants = Arrays.asList(
                new Restaurant(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new Restaurant(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantRepository.findAll()).thenReturn(mockRestaurants);

        // Call the service method
        List<RestaurantDTO> restaurantDTOList = restaurantService.fetchAllRestaurants();

        // Verify the result
        assertEquals(mockRestaurants.size(), restaurantDTOList.size());
        for (int i = 0; i < mockRestaurants.size(); i++) {
            RestaurantDTO expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(mockRestaurants.get(i));
            assertEquals(expectedDTO, restaurantDTOList.get(i));
        }

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findAll();
    }
	
	@Test
	public void testSaveRestaurant() {
		//create test data
		
		RestaurantDTO mockRestaurantDTO = new RestaurantDTO(1,"Restaurant 1","Address 1", "City 1", "Description 1");
		Restaurant mockRestaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(mockRestaurantDTO);
		
		//mock the repository behavior
		when(restaurantRepository.save(mockRestaurant)).thenReturn(mockRestaurant);
		
		//call the service method
		RestaurantDTO  savedRestaurantDTO = restaurantService.saveRestaurant(mockRestaurantDTO);
		
		//verify the result
		assertEquals(mockRestaurantDTO,savedRestaurantDTO);
		
	     // Verify that the repository method was called
        verify(restaurantRepository, times(1)).save(mockRestaurant);
		
	}
	
	@Test
	public void testFindRestaurantById() {
		
        // Create a mock restaurant ID
        Integer mockRestaurantId = 1;
        
        //create a mock restaurant to be returned by repository 
        Restaurant mockRestaurant = new Restaurant(1,"Restaurant 1","Address 1", "City 1", "Description 1");
        
        //mock the repository behavior
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));
        
        //call the service method
        ResponseEntity<RestaurantDTO> responseEntity = restaurantService.findRestaurantById(mockRestaurantId);
        
        //verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockRestaurantId, responseEntity.getBody().getId());
        
        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
		
	}
	
	 @Test
	    public void testFindRestaurantById_NonExistingId() {
	        // Create a mock non-existing restaurant ID
	        Integer mockRestaurantId = 1;

	        // Mock the repository behavior
	        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.empty());

	        // Call the service method
	        ResponseEntity<RestaurantDTO> response = restaurantService.findRestaurantById(mockRestaurantId);

	        // Verify the response
	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	        assertEquals(null, response.getBody());

	        // Verify that the repository method was called
	        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
	    }
	

}
