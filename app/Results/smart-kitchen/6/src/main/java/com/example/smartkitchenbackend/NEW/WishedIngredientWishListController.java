package com.example.smartkitchenbackend.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.entities.WishedIngredient;
@RestController
@CrossOrigin
public class WishedIngredientWishListController {

@Autowired
 private WishedIngredientWishListService wishedingredientwishlistservice;


}