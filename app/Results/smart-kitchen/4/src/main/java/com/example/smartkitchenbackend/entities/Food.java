package com.example.smartkitchenbackend.entities;
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
@Entity
@Data
public class Food {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  String name;

@Transient
 private  List<NeededIngredient> ingredients;

@Transient
 private  Kitchen kitchen;

@Transient
 private NeededIngredientRequest neededingredientrequest = new NeededIngredientRequestImpl();;

@Column(name = "id")
 private long id;

@Transient
 private KitchenRequest kitchenrequest = new KitchenRequestImpl();;


}