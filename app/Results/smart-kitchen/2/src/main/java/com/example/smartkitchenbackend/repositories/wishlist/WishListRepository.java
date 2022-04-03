package com.example.smartkitchenbackend.repositories.wishlist;
 import com.example.smartkitchenbackend.entities.WishList;
public interface WishListRepository {


public WishList findReference(long wishListId)
;

public WishList findById(long id)
;

public WishList save(WishList wishList)
;

public void delete(WishList wishList)
;

}