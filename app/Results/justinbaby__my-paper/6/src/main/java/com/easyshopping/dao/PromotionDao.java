package com.easyshopping.dao;
 import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Promotion;
public interface PromotionDao extends BaseDao<Promotion, Long>{


public List<Promotion> findList(Boolean hasBegun,Boolean hasEnded,Integer count,List<Filter> filters,List<Order> orders)
;

}