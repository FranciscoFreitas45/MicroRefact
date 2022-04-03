package com.example.smartkitchenbackend.controllers;
 import com.example.smartkitchenbackend.DTOs.Food.FoodDTO;
import com.example.smartkitchenbackend.DTOs.Food.FoodDetailDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

 private  FoodService foodService;


@GetMapping("/getMissingIngredients/{foodId}")
public List<NewIngredientDTO> getMissingIngredients(long foodId){
    return foodService.getMissingIngredientsForFood(foodId);
}


@GetMapping("/getMakeAbleFoods/{kitchenId}")
public List<FoodDetailDTO> getMakeableFoods(long kitchenId){
    return foodService.getMakeableFoodsInKitchen(kitchenId);
}


@GetMapping("/listFoods/{kitchenId}")
public List<FoodDetailDTO> getFoodsByKitchenId(long kitchenId){
    return foodService.getFoodsByKitchenId(kitchenId);
}


@PostMapping("/createFood")
public void createFood(FoodDTO foodDTO){
    foodService.create(foodDTO);
}


@GetMapping("/getFoodDetail/{foodId}")
public FoodDetailDTO getFoodDetails(long foodId){
    return foodService.getFoodDetails(foodId);
}


}