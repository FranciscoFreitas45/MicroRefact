package com.example.smartkitchenbackend.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.entities.WishList;
@RestController
@CrossOrigin
public class WishListKitchenController {

@Autowired
 private WishListKitchenService wishlistkitchenservice;


}