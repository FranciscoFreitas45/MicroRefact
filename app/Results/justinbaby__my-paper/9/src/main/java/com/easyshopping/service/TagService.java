package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Tag;
import com.easyshopping.entity.Tag.Type;
public interface TagService extends BaseService<Tag, Long>{


public List<Tag> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

}