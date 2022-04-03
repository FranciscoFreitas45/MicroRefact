package com.example.smartkitchenbackend.repositories.food;
 import com.example.smartkitchenbackend.entities.Food;
public interface FoodRepository {


public Food findReference(long id)
;

public Food findById(long id)
;

public Food save(Food food)
;

public void delete(Food food)
;

}