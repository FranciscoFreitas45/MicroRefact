package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
public class Kitchen {

 private  long id;

 private  String name;

 private  List<User> users;

 private  List<IngredientInKitchen> ingredients;

 private  WishList wishList;

 private  List<Food> foods;


}