package com.example.demo.service;
 import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import com.example.demo.model.CartItem;
@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService{

 private  Map<Integer,CartItem> map;


@Override
public void add(CartItem item){
    CartItem cartItem = map.get(item.getProductid());
    if (cartItem != null) {
        item.setQty(item.getQty() + 1);
        System.out.println(cartItem.getName());
    } else {
        map.put(item.getProductid(), item);
    }
}


@Override
public Collection<CartItem> getItems(){
    return map.values();
}


@Override
public void clear(){
    map.clear();
}


@Override
public CartItem update(Integer id,int qty){
    CartItem cartItem = map.get(id);
    cartItem.setQty(qty);
    return cartItem;
}


@Override
public int getCount(){
    return map.values().stream().mapToInt(item -> item.getQty()).sum();
}


@Override
public void remove(Integer id){
    map.remove(id);
}


@Override
public double getAmount(){
    return map.values().stream().mapToDouble(item -> item.getPrice() * item.getQty()).sum();
}


}