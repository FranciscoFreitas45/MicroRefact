package com.example.smartkitchenbackend.services;
 import com.example.smartkitchenbackend.DTOs.Food.FoodDTO;
import com.example.smartkitchenbackend.DTOs.Food.FoodDetailDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.entities.Food;
import com.example.smartkitchenbackend.entities.Kitchen;
import com.example.smartkitchenbackend.expection.BadRequestException;
import com.example.smartkitchenbackend.repositories.food.FoodRepository;
import com.example.smartkitchenbackend.services.Converters.FoodConverter;
import com.example.smartkitchenbackend.services.Converters.IngredientConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class FoodService {

 private  FoodRepository foodRepository;

 private  KitchenService kitchenService;

 private  IngredientService ingredientService;


public boolean isTheIngredientNotInTheKitchen(List<IngredientDTO> ingredientsInKitchen,IngredientDTO ingredientInFood){
    final Boolean[] isInTheKitchen = { false };
    ingredientsInKitchen.forEach(ingredientInKitchen -> {
        if (ingredientInKitchen.getName().equals(ingredientInFood.getName()))
            isInTheKitchen[0] = true;
    });
    return !isInTheKitchen[0];
}


public List<FoodDetailDTO> getMakeableFoodsInKitchen(long kitchenId){
    List<FoodDetailDTO> foods = getFoodsByKitchenId(kitchenId);
    List<FoodDetailDTO> makeableFoods = new ArrayList<>();
    List<IngredientDTO> ingredientsInKitchen = getConvertedIngredientsInKitchen(kitchenService.findById(kitchenId));
    foods.forEach(foodDetailDTO -> {
        if (filtering(ingredientsInKitchen, foodDetailDTO))
            makeableFoods.add(foodDetailDTO);
    });
    return makeableFoods;
}


public List<IngredientDTO> getConvertedIngredientsInKitchen(Kitchen kitchen){
    return kitchen.getIngredients().stream().map(ingredientInKitchen -> IngredientConverter.toIngredientDTO(ingredientInKitchen.getWeightOrCount(), ingredientInKitchen.getIngredient().getName(), ingredientInKitchen.getIngredient().getType(), ingredientInKitchen.getId())).collect(Collectors.toList());
}


public void create(FoodDTO foodDTO){
    if (foodDTO.getName() == null)
        throw new BadRequestException("Food name can not be empty");
    Food food = new Food();
    food.setKitchen(kitchenService.findById(foodDTO.getKitchenId()));
    food.setName(foodDTO.getName());
    foodRepository.save(food);
    food.setIngredients(foodDTO.getIngredients().stream().map(newIngredientDTO -> ingredientService.createInFood(newIngredientDTO, food.getId())).collect(Collectors.toList()));
    foodRepository.save(food);
}


public List<FoodDetailDTO> getFoodsByKitchenId(long kitchenId){
    return kitchenService.findById(kitchenId).getFoods().stream().map(food -> FoodConverter.toFoodDetailDTO(food, getConvertedIngredientsInFood(food))).collect(Collectors.toList());
}


public List<IngredientDTO> getConvertedIngredientsInFood(Food food){
    return food.getIngredients().stream().map(neededIngredient -> IngredientConverter.toIngredientDTO(neededIngredient.getWeightOrCount(), neededIngredient.getIngredient().getName(), neededIngredient.getIngredient().getType(), neededIngredient.getId())).collect(Collectors.toList());
}


public List<NewIngredientDTO> getMissingIngredientsForFood(long foodId){
    Food food = foodRepository.findById(foodId);
    Kitchen kitchen = food.getKitchen();
    List<NewIngredientDTO> missingIngredients = new ArrayList<>();
    List<IngredientDTO> ingredientsInKitchen = getConvertedIngredientsInKitchen(kitchen);
    getConvertedIngredientsInFood(food).forEach(ingredientInFood -> {
        ingredientsInKitchen.forEach(ingredientInKitchen -> {
            if (ingredientInFood.getName().equals(ingredientInKitchen.getName())) {
                if (ingredientInFood.getWeightOrCount() > ingredientInKitchen.getWeightOrCount())
                    missingIngredients.add(NewIngredientDTO.builder().name(ingredientInFood.getName()).weightOrCount(ingredientInFood.getWeightOrCount() - ingredientInKitchen.getWeightOrCount()).type(ingredientInFood.getType()).build());
            }
        });
        if (isTheIngredientNotInTheKitchen(ingredientsInKitchen, ingredientInFood))
            missingIngredients.add(NewIngredientDTO.builder().name(ingredientInFood.getName()).weightOrCount(ingredientInFood.getWeightOrCount()).type(ingredientInFood.getType()).build());
    });
    return missingIngredients;
}


public boolean filtering(List<IngredientDTO> ingredientsInKitchen,FoodDetailDTO foodDetailDTO){
    final int[] numberOfIngredientsFound = { 0 };
    foodDetailDTO.getIngredients().forEach(ingredientDTO -> {
        ingredientsInKitchen.forEach(ingredient -> {
            if (ingredient.getName().equals(ingredientDTO.getName()) && ingredient.getWeightOrCount() >= ingredientDTO.getWeightOrCount())
                numberOfIngredientsFound[0]++;
        });
    });
    return numberOfIngredientsFound[0] == foodDetailDTO.getIngredients().size();
}


public FoodDetailDTO getFoodDetails(long foodId){
    Food food = foodRepository.findById(foodId);
    return FoodConverter.toFoodDetailDTO(food, getConvertedIngredientsInFood(food));
}


}