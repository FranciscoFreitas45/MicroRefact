package com.example.smartkitchenbackend.entities;
 import lombok.Data;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.example.smartkitchenbackend.Request.WishedIngredientRequest;
import com.example.smartkitchenbackend.Request.Impl.WishedIngredientRequestImpl;
import com.example.smartkitchenbackend.DTO.WishedIngredient;
@Data
@Entity
public class Ingredient {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  String name;

 private  String type;

@Transient
 private  List<WishedIngredient> wishLists;

@OneToMany(mappedBy = "ingredient")
 private  List<IngredientInKitchen> kitchens;

@OneToMany(mappedBy = "ingredient")
 private  List<NeededIngredient> foods;

@Transient
 private WishedIngredientRequest wishedingredientrequest = new WishedIngredientRequestImpl();;


}