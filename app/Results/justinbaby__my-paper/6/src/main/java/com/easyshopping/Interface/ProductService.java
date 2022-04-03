package com.easyshopping.Interface;
public interface ProductService {

   public List<Product> search(String keyword,Boolean isGift,Integer count);
   public List<Product> findList(ProductCategory productCategory,Date beginDate,Date endDate,Integer first,Integer count);
   public Object find(Object Object);
}