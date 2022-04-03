package com.example.smartkitchenbackend.repositories.ingerdientInKitchen;
 import com.example.smartkitchenbackend.entities.IngredientInKitchen;
public interface IngredientInKitchenRepository {


public Boolean existsByIngredientIdAndKitchenId(long ingredientId,long kitchenId)
;

public IngredientInKitchen findByIngredientIdAndKitchenId(long ingredientId,long kitchenId)
;

public IngredientInKitchen findById(long id)
;

public IngredientInKitchen save(IngredientInKitchen ingredientInKitchen)
;

public void deleteById(long ingredientInKitchenId)
;

}