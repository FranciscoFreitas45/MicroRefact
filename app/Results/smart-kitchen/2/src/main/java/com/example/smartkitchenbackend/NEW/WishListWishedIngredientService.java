package com.example.smartkitchenbackend.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartkitchenbackend.repositories.wishlist.WishListJPARepository;
import com.example.smartkitchenbackend.entities.WishList;
@Service
public class WishListWishedIngredientService {

@Autowired
 private WishListJPARepository wishlistjparepository;


}