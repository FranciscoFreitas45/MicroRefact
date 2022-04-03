package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import javax.persistence;
import com.example.smartkitchenbackend.Request.FoodRequest;
import com.example.smartkitchenbackend.Request.Impl.FoodRequestImpl;
import com.example.smartkitchenbackend.DTO.Food;
public class NeededIngredient {

 private  long id;

 private  double weightOrCount;

 private  Ingredient ingredient;

 private  Food food;

 private long id;


}