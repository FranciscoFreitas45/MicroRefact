package com.easyshopping.Interface;
public interface PromotionService {

   public List<Promotion> findList(Boolean hasBegun,Boolean hasEnded,Integer count,List<Filter> filters,List<Order> orders,String cacheRegion);
}