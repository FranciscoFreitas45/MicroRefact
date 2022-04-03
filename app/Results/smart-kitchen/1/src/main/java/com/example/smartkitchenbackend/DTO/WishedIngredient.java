package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import javax.persistence;
public class WishedIngredient {

 private  long id;

 private  double weightOrCount;

 private  Ingredient ingredient;

 private  WishList wishList;


}