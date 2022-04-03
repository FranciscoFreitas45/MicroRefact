package com.example.smartkitchenbackend.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.entities.NeededIngredient;
@RestController
@CrossOrigin
public class NeededIngredientFoodController {

@Autowired
 private NeededIngredientFoodService neededingredientfoodservice;


}