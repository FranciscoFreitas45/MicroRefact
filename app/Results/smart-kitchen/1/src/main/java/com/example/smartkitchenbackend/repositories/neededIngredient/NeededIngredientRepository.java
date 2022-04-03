package com.example.smartkitchenbackend.repositories.neededIngredient;
 import com.example.smartkitchenbackend.entities.NeededIngredient;
public interface NeededIngredientRepository {


public Boolean existsByIngredientIdAndFoodId(long id,long foodId)
;

public NeededIngredient findById(long id)
;

public NeededIngredient save(NeededIngredient neededIngredient)
;

public NeededIngredient findByIngredientIdAndFoodId(long id,long foodId)
;

public void delete(NeededIngredient neededIngredient)
;

}