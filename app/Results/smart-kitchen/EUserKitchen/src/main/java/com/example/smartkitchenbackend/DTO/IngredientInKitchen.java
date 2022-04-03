package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence;
import com.example.smartkitchenbackend.Request.KitchenRequest;
import com.example.smartkitchenbackend.Request.Impl.KitchenRequestImpl;
import com.example.smartkitchenbackend.DTO.Kitchen;
public class IngredientInKitchen {

 private  long id;

 private  double weightOrCount;

 private  Ingredient ingredient;

 private  Kitchen kitchen;

 private long id;


}