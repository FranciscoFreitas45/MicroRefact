package com.easyshopping.Interface;
public interface PromotionDao {

   public List<Promotion> findList(Boolean hasBegun,Boolean hasEnded,Integer count,List<Filter> filters,List<Order> orders);
   public Object clear(Object Object);
   public Object flush(Object Object);
}