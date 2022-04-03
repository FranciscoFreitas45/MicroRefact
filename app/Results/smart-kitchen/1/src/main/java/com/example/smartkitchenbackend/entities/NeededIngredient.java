package com.example.smartkitchenbackend.entities;
 import lombok.Data;
import javax.persistence;
import com.example.smartkitchenbackend.Request.FoodRequest;
import com.example.smartkitchenbackend.Request.Impl.FoodRequestImpl;
import com.example.smartkitchenbackend.DTO.Food;
@Entity
@Data
public class NeededIngredient {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  double weightOrCount;

@ManyToOne
 private  Ingredient ingredient;

@Transient
 private  Food food;

@Column(name = "id")
 private long id;

@Transient
 private FoodRequest foodrequest = new FoodRequestImpl();;


}