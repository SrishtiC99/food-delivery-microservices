package com.srishti.restaurantservice.controller;

import com.srishti.restaurantservice.dto.FoodItemDto;
import com.srishti.restaurantservice.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/fooditem")
@RequiredArgsConstructor
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addFoodItem(@RequestBody FoodItemDto foodItemDto) {
        return foodItemService.addFoodItem(foodItemDto);
    }

    @GetMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodItemDto> getAllFoodItems(@PathVariable("restaurantId") String restaurantId) {
        return foodItemService.getAllFoodItems(restaurantId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String updateFoodItem(@RequestBody FoodItemDto foodItemDto) {
        return foodItemService.updateFoodItem(foodItemDto);
    }

}
