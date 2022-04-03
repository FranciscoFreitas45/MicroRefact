package com.example.smartkitchenbackend.repositories.ingredient;
 import com.example.smartkitchenbackend.entities.Ingredient;
public interface IngredientRepository {


public Boolean existsByName(String name)
;

public Ingredient findById(long id)
;

public Ingredient save(Ingredient ingredient)
;

public Ingredient findByName(String name)
;

public void delete(Ingredient ingredient)
;

}