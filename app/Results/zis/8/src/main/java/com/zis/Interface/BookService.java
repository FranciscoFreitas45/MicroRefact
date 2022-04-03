package com.zis.Interface;
public interface BookService {

   public Bookinfo findBookById(int id);
   public ShopItemInfo findShopItemByBookIdAndShopName(String shopName,Integer bookId);
}