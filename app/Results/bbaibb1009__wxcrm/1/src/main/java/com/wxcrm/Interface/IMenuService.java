package com.wxcrm.Interface;
public interface IMenuService {

   public List<Map<String,Object>> queryMenuToCache();
   public List<Map<String,Object>> queryShopMenuToCache();
}