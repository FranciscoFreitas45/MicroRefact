package com.Interface;
public interface IShopCartService {

   public List<Map<String,Object>> queryShopCartList(Page page,int userId);
}