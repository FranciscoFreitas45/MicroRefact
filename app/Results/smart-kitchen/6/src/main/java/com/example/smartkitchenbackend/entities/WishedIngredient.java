package com.example.smartkitchenbackend.entities;
 import lombok.Data;
import javax.persistence;
import com.example.smartkitchenbackend.Request.IngredientRequest;
import com.example.smartkitchenbackend.Request.Impl.IngredientRequestImpl;
import com.example.smartkitchenbackend.DTO.Ingredient;
import com.example.smartkitchenbackend.Request.WishListRequest;
import com.example.smartkitchenbackend.Request.Impl.WishListRequestImpl;
import com.example.smartkitchenbackend.DTO.WishList;
@Data
@Entity
public class WishedIngredient {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  double weightOrCount;

@Transient
 private  Ingredient ingredient;

@Transient
 private  WishList wishList;

@Column(name = "id")
 private long id;

@Transient
 private IngredientRequest ingredientrequest = new IngredientRequestImpl();;

@Column(name = "id")
 private long id;

@Transient
 private WishListRequest wishlistrequest = new WishListRequestImpl();;


}