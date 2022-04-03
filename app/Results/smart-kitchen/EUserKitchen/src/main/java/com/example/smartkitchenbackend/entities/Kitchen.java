package com.example.smartkitchenbackend.entities;
 import lombok.Data;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import com.example.smartkitchenbackend.Request.IngredientInKitchenRequest;
import com.example.smartkitchenbackend.Request.Impl.IngredientInKitchenRequestImpl;
import com.example.smartkitchenbackend.DTO.IngredientInKitchen;
import com.example.smartkitchenbackend.Request.WishListRequest;
import com.example.smartkitchenbackend.Request.Impl.WishListRequestImpl;
import com.example.smartkitchenbackend.DTO.WishList;
import com.example.smartkitchenbackend.Request.FoodRequest;
import com.example.smartkitchenbackend.Request.Impl.FoodRequestImpl;
import com.example.smartkitchenbackend.DTO.Food;
@Entity
@Data
public class Kitchen {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  String name;

@ManyToMany
 private  List<User> users;

@Transient
 private  List<IngredientInKitchen> ingredients;

@Transient
 private  WishList wishList;

@Transient
 private  List<Food> foods;

@Transient
 private IngredientInKitchenRequest ingredientinkitchenrequest = new IngredientInKitchenRequestImpl();;

@Column(name = "id")
 private long id;

@Transient
 private WishListRequest wishlistrequest = new WishListRequestImpl();;

@Transient
 private FoodRequest foodrequest = new FoodRequestImpl();;


public void addUser(User user){
    users.add(user);
    user.addKitchen(this);
}


}