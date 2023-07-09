package com.srishti.restaurantservice.service;

import com.srishti.restaurantservice.dto.FoodItemDto;
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
public class FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public String addFoodItem(FoodItemDto foodItemDto, String username) {
        // check if the loggedInUser is same as the restaurant Owner or not
        Restaurant restaurant = restaurantRepository.findById(foodItemDto.getRestaurantId()).get();
        if(!restaurant.getOwner().getUsername().equals(username)) {
            return "Access Denied...You are not the OWNER of this restaurant";
        }
        FoodItem foodItem = FoodItem.builder()
                .name(foodItemDto.getName())
                .description(foodItemDto.getDescription())
                .quantity(foodItemDto.getQuantity())
                .icon(foodItemDto.getIcon())
                .price(foodItemDto.getPrice())
                .restaurantId(foodItemDto.getRestaurantId())
                .build();
        foodItemRepository.save(foodItem);
        return "Food item added with id: " + foodItem.getId();
    }

    public List<FoodItemDto> getAllFoodItems(String restaurantId) {
        List<FoodItem> allFoodItems =  foodItemRepository.findByRestaurantId(restaurantId);
        return allFoodItems.stream().map(foodItem ->
                FoodItemDto.builder()
                        .id(foodItem.getId())
                        .name(foodItem.getName())
                        .description(foodItem.getDescription())
                        .icon(foodItem.getIcon())
                        .quantity(foodItem.getQuantity())
                        .price(foodItem.getPrice())
                        .restaurantId(foodItem.getRestaurantId())
                        .build()).toList();
    }

    public String updateFoodItem(FoodItemDto foodItemDto, String username) {
        // check if the loggedInUser is same as the restaurant Owner or not
        Restaurant restaurant = restaurantRepository.findById(foodItemDto.getRestaurantId()).get();
        if(!restaurant.getOwner().getUsername().equals(username)) {
            return "Access Denied...You are not the OWNER of this restaurant";
        }
        Optional<FoodItem> foodItem = foodItemRepository.findById(foodItemDto.getId());
        if(foodItem.isPresent()) {
            update(foodItem.get(), foodItemDto);
            return "Updated Successfully";
        }
        return "Food Item not present";
    }

    private void update(FoodItem item, FoodItemDto foodItemDto) {
        item.setName(foodItemDto.getName());
        item.setDescription(foodItemDto.getDescription());
        item.setIcon(foodItemDto.getIcon());
        item.setPrice(foodItemDto.getPrice());
        item.setQuantity(foodItemDto.getQuantity());
        foodItemRepository.save(item);
    }

    public void updateFoodItemQuantity(List<String> foodItemIds, List<Integer> orderQuantities) {
        for(int i=0; i<foodItemIds.size(); i++) {
            updateFoodItemQuantity(foodItemIds.get(i), orderQuantities.get(i));
        }
    }

    private void updateFoodItemQuantity(String foodItemId, Integer quantity) {
        Optional<FoodItem> foodItem = foodItemRepository.findById(foodItemId);
        if(foodItem.isPresent()) {
            FoodItem foodItem1 = foodItem.get();
            foodItem1.setQuantity(foodItem1.getQuantity() - quantity);
            foodItemRepository.save(foodItem1);
        }
    }

}
