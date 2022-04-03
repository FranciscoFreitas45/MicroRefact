package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
public class Food {

 private  long id;

 private  String name;

 private  List<NeededIngredient> ingredients;

 private  Kitchen kitchen;


}