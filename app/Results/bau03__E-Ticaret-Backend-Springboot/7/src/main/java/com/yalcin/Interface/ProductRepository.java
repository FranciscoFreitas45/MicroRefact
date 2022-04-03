package com.yalcin.Interface;
public interface ProductRepository {

   public Product findAllById(Integer productId);
   public Object save(Object Object);
}