package com.example.demo.service;
 import java.util.Collection;
import com.example.demo.model.CartItem;
public interface ShoppingCartService {


public void add(CartItem item)
;

public Collection<CartItem> getItems()
;

public void clear()
;

public CartItem update(Integer id,int qty)
;

public int getCount()
;

public void remove(Integer id)
;

public double getAmount()
;

}