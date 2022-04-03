package com.example.smartkitchenbackend.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.repositories.neededIngredient.NeededIngredientJPARepository;
import com.example.smartkitchenbackend.entities.NeededIngredient;
@Service
public class NeededIngredientFoodService {

@Autowired
 private NeededIngredientJPARepository neededingredientjparepository;


}