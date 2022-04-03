package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence;
public class IngredientInKitchen {

 private  long id;

 private  double weightOrCount;

 private  Ingredient ingredient;

 private  Kitchen kitchen;

 private long id;


}