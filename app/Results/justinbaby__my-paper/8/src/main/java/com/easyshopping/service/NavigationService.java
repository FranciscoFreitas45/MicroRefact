package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Navigation;
import com.easyshopping.entity.Navigation.Position;
public interface NavigationService extends BaseService<Navigation, Long>{


public List<Navigation> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

}