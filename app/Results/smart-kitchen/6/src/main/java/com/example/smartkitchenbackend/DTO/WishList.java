package com.example.smartkitchenbackend.DTO;
 import lombok.Data;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import com.example.smartkitchenbackend.Request.WishedIngredientRequest;
import com.example.smartkitchenbackend.Request.Impl.WishedIngredientRequestImpl;
import com.example.smartkitchenbackend.DTO.WishedIngredient;
import com.example.smartkitchenbackend.Request.KitchenRequest;
import com.example.smartkitchenbackend.Request.Impl.KitchenRequestImpl;
import com.example.smartkitchenbackend.DTO.Kitchen;
public class WishList {

 private  long id;

 private  List<WishedIngredient> ingredients;

 private  Kitchen kitchen;

 private long id;


}