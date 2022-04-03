package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import javax.persistence;
public class NeededIngredient {

 private  long id;

 private  double weightOrCount;

 private  Ingredient ingredient;

 private  Food food;

 private long id;


}