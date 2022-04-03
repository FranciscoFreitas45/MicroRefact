package com.example.smartkitchenbackend.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.entities.Food;
@RestController
@CrossOrigin
public class FoodNeededIngredientController {

@Autowired
 private FoodNeededIngredientService foodneededingredientservice;


}