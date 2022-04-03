package com.yalcin.Request;
import com.yalcin.DTO.Product;
public interface ProductRequest {

   public void setProduct(Product product,Integer id);
   public Product getProduct(Integer id);
}