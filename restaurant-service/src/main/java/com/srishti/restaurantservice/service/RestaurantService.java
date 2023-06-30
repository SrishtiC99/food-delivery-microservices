package com.srishti.restaurantservice.service;

import com.srishti.restaurantservice.dto.RestaurantRequest;
import com.srishti.restaurantservice.dto.RestaurantResponse;
import com.srishti.restaurantservice.model.FoodItem;
import com.srishti.restaurantservice.model.Restaurant;
import com.srishti.restaurantservice.repository.FoodItemRepository;
import com.srishti.restaurantservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodItemRepository foodItemRepository;
    public String addRestaurant(RestaurantRequest request) {
        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .description(request.getDescription())
                .contactInfo(request.getContactInfo())
                .address(request.getAddress())
                .rating(request.getRating())
                .build();
        restaurantRepository.save(restaurant);
        return "Restaurant with id: " + restaurant.getId() + "added successfully";
    }

    public RestaurantResponse getRestaurant(String id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        if(restaurant.isPresent()) {
            updateResponse(restaurant.get(), restaurantResponse);
        }
        return restaurantResponse;
    }

    private void updateResponse(Restaurant restaurant, RestaurantResponse restaurantResponse) {
        restaurantResponse.setId(restaurant.getId());
        restaurantResponse.setName(restaurant.getName());
        restaurantResponse.setDescription(restaurant.getDescription());
        restaurantResponse.setAddress(restaurant.getAddress());
        restaurantResponse.setContactInfo(restaurant.getContactInfo());
        restaurantResponse.setRating(restaurant.getRating());
        List<FoodItem> foodItems = foodItemRepository.findByRestaurantId(restaurantResponse.getId());
        restaurantResponse.setFoodItems(foodItems);
    }

    public List<RestaurantResponse> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(this::mapToResponse).toList();
    }
    
    private RestaurantResponse mapToResponse(Restaurant restaurant) {
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        restaurantResponse.setId(restaurant.getId());
        restaurantResponse.setName(restaurant.getName());
        restaurantResponse.setDescription(restaurant.getDescription());
        restaurantResponse.setAddress(restaurant.getAddress());
        restaurantResponse.setContactInfo(restaurant.getContactInfo());
        restaurantResponse.setRating(restaurant.getRating());
        List<FoodItem> foodItems = foodItemRepository.findByRestaurantId(restaurantResponse.getId());
        restaurantResponse.setFoodItems(foodItems);
        return restaurantResponse;
    }
}
