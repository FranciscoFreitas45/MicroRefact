package com.zis.Interface;
public interface BookService {

   public void saveShopItemForBatch(List<ShopItemInfoDTO> list);
   public void updateTitleAndCategoryForBatch(List<ShopItemInfoDTO> list);
}