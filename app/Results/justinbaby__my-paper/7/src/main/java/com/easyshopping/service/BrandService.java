package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.Brand;
public interface BrandService extends BaseService<Brand, Long>{


public List<Brand> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

}