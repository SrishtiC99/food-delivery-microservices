package com.srishti.restaurantservice.repository;

import com.srishti.restaurantservice.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}
