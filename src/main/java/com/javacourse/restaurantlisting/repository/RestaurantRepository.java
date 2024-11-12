package com.javacourse.restaurantlisting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacourse.restaurantlisting.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
