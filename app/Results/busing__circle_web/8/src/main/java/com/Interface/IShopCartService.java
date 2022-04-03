package com.Interface;
public interface IShopCartService {

   public Map<String,List<Map<String,Object>>> queryCalShopCartList(int userId,String calCartId);
   public double calTotal(String[] cartId);
}