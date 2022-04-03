package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.example.smartkitchenbackend.Request.WishedIngredientRequest;
import com.example.smartkitchenbackend.Request.Impl.WishedIngredientRequestImpl;
import com.example.smartkitchenbackend.DTO.WishedIngredient;
public class Ingredient {

 private  long id;

 private  String name;

 private  String type;

 private  List<WishedIngredient> wishLists;

 private  List<IngredientInKitchen> kitchens;

 private  List<NeededIngredient> foods;


}