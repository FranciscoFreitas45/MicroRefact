package com.example.smartkitchenbackend.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.repositories.kitchen.KitchenJPARepository;
import com.example.smartkitchenbackend.entities.Kitchen;
@Service
public class KitchenWishListService {

@Autowired
 private KitchenJPARepository kitchenjparepository;


}