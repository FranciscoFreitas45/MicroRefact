package com.example.smartkitchenbackend.controllers;
 import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientController {

 private  IngredientService ingredientService;


@PostMapping("/createInKitchen/{kitchenId}")
public IngredientDTO createIngredientInKitchen(NewIngredientDTO newIngredientDTO,long kitchenId){
    return ingredientService.createInKitchen(newIngredientDTO, kitchenId);
}


@PostMapping("/createInWishList/{wishListId}")
public IngredientDTO createIngredientInWishList(NewIngredientDTO newIngredientDTO,long wishListId){
    return ingredientService.createInWishList(newIngredientDTO, wishListId);
}


@DeleteMapping("/removeFromKitchen/{ingredientInKitchenId}")
public void removeFromKitchen(long ingredientInKitchenId){
    ingredientService.removeFromKitchen(ingredientInKitchenId);
}


@PostMapping("createMultipleInWishListFromFood/{foodId}")
public void createMultipleInWishListFromFood(List<NewIngredientDTO> newIngredientDTO,long foodId){
    ingredientService.createMultipleInWishList(newIngredientDTO, foodId);
}


@DeleteMapping("/removeFromWishList/{wishedIngredientId}")
public void removeFromWishList(long wishedIngredientId){
    ingredientService.removeFromWishList(wishedIngredientId);
}


@PostMapping("createMultipleInKitchenFromWishList/{wishListId}")
public void createMultipleInKitchen(List<NewIngredientDTO> newIngredientDTO,long wishListId){
    ingredientService.createMultipleInKitchen(newIngredientDTO, wishListId);
}


}