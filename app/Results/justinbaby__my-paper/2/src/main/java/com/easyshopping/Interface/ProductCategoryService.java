package com.easyshopping.Interface;
public interface ProductCategoryService {

   public List<ProductCategory> findRoots(Integer count,String cacheRegion);
}