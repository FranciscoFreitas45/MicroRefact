package com.weflors.Interface;
public interface ProductStatusServiceImpl {

   public ProductStatusEntity findProductStatusEntity(Integer productId);
   public void updateQuantityWriteoffAndWarehouse(Integer productId,Integer quantityWriteoff);
}