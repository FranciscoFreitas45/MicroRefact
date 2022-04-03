package com.example.smartkitchenbackend.repositories.wishedIngredient;
 import com.example.smartkitchenbackend.entities.WishedIngredient;
public interface WishedIngredientRepository {


public WishedIngredient findByIngredientIdAndWishListId(long id,long wishListId)
;

public WishedIngredient findById(long id)
;

public WishedIngredient save(WishedIngredient wishedIngredient)
;

public Boolean existsByIngredientIdAndWishListId(long id,long wishListId)
;

public void deleteById(long wishedIngredientId)
;

public void delete(WishedIngredient wishedIngredient)
;

}