package com.easyshopping.Interface;
public interface BrandService {

   public Object findAll(Object Object);
   public List<Brand> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion);
}