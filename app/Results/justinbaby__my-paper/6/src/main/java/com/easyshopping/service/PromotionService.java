package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Promotion;
public interface PromotionService extends BaseService<Promotion, Long>{


public List<Promotion> findList(Boolean hasBegun,Boolean hasEnded,Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

}