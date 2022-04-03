package com.easyshopping.Interface;
public interface CartService {

   public Cart getCurrent();
   public void merge(Member member,Cart cart);
}