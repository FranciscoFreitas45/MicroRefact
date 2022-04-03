package com.easyshopping.Interface;
public interface ReviewService {

   public List<Review> findList(Member member,Product product,Type type,Boolean isShow,Integer count,List<Filter> filters,List<Order> orders,String cacheRegion);
}