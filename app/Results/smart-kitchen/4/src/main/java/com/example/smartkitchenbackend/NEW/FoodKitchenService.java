package com.example.smartkitchenbackend.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.repositories.food.FoodJPARepository;
import com.example.smartkitchenbackend.entities.Food;
@Service
public class FoodKitchenService {

@Autowired
 private FoodJPARepository foodjparepository;


}