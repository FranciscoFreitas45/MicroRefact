package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import javax.persistence;
import com.example.smartkitchenbackend.Request.IngredientRequest;
import com.example.smartkitchenbackend.Request.Impl.IngredientRequestImpl;
import com.example.smartkitchenbackend.DTO.Ingredient;
import com.example.smartkitchenbackend.Request.WishListRequest;
import com.example.smartkitchenbackend.Request.Impl.WishListRequestImpl;
import com.example.smartkitchenbackend.DTO.WishList;
public class WishedIngredient {

 private  long id;

 private  double weightOrCount;

 private  Ingredient ingredient;

 private  WishList wishList;

 private long id;

 private IngredientRequest ingredientrequest = new IngredientRequestImpl();;

 private long id;

 private WishListRequest wishlistrequest = new WishListRequestImpl();;


}