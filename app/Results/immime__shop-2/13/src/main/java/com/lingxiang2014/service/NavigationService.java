package com.lingxiang2014.service;
 import java.util.List;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.entity.Navigation;
import com.lingxiang2014.entity.Navigation.Position;
public interface NavigationService extends BaseService<Navigation, Long>{


public List<Navigation> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

}