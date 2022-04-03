package com.lingxiang2014.Interface;
public interface TagService {

   public List<Tag> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion);
}