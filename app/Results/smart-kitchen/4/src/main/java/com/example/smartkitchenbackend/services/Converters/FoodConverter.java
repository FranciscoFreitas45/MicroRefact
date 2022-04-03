package com.example.smartkitchenbackend.services.Converters;
 import com.example.smartkitchenbackend.DTOs.Food.FoodDTO;
import com.example.smartkitchenbackend.DTOs.Food.FoodDetailDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.entities.Food;
import lombok.experimental.UtilityClass;
import java.util.List;
@UtilityClass
public class FoodConverter {


public FoodDetailDTO toFoodDetailDTO(Food food,List<IngredientDTO> ingredients){
    return FoodDetailDTO.builder().name(food.getName()).id(food.getId()).ingredients(ingredients).build();
}


public FoodDTO toFoodDTO(Food food,List<NewIngredientDTO> ingredients){
    return FoodDTO.builder().name(food.getName()).kitchenId(food.getKitchen().getId()).ingredients(ingredients).build();
}


}