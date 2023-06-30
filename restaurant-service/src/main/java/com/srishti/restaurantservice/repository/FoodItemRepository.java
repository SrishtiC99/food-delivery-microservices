package com.srishti.restaurantservice.repository;

import com.srishti.restaurantservice.model.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
    List<FoodItem> findByRestaurantId(String restaurantId);
}
