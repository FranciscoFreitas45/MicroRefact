package com.Request;

import com.DTO.Dish;

public interface DishRequest {

   public Dish getDishId(Long dishIdv2);
   public void setDishId(Dish dishId,Long dishIdv2);
}