package com.srishti.restaurantservice.service;

import com.srishti.restaurantservice.dto.FoodItemDto;
import com.srishti.restaurantservice.model.FoodItem;
import com.srishti.restaurantservice.repository.FoodItemRepository;
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

    public String addFoodItem(FoodItemDto foodItemDto) {
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

    public String updateFoodItem(FoodItemDto foodItemDto) {
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
}
