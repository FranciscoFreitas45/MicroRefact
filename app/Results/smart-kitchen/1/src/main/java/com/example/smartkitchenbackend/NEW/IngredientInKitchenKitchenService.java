package com.example.smartkitchenbackend.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.repositories.ingerdientInKitchen.IngredientInKitchenJPARepository;
import com.example.smartkitchenbackend.entities.IngredientInKitchen;
@Service
public class IngredientInKitchenKitchenService {

@Autowired
 private IngredientInKitchenJPARepository ingredientinkitchenjparepository;


}