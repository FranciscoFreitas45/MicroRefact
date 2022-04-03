package com.easyshopping.Interface;
public interface ProductService {

   public List<Product> findList(ProductCategory productCategory,Date beginDate,Date endDate,Integer first,Integer count);
}