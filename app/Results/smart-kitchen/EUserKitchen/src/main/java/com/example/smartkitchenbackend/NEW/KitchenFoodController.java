package com.example.smartkitchenbackend.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.entities.Kitchen;
@RestController
@CrossOrigin
public class KitchenFoodController {

@Autowired
 private KitchenFoodService kitchenfoodservice;


}