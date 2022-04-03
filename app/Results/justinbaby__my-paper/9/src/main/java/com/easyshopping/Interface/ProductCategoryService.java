package com.easyshopping.Interface;
public interface ProductCategoryService {

   public List<ProductCategory> findChildren(ProductCategory productCategory,Integer count,String cacheRegion);
   public Object find(Object Object);
}