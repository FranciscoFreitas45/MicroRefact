package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import com.example.smartkitchenbackend.Request.NeededIngredientRequest;
import com.example.smartkitchenbackend.Request.Impl.NeededIngredientRequestImpl;
import com.example.smartkitchenbackend.DTO.NeededIngredient;
import com.example.smartkitchenbackend.Request.KitchenRequest;
import com.example.smartkitchenbackend.Request.Impl.KitchenRequestImpl;
import com.example.smartkitchenbackend.DTO.Kitchen;
public class Food {

 private  long id;

 private  String name;

 private  List<NeededIngredient> ingredients;

 private  Kitchen kitchen;

 private long id;


}