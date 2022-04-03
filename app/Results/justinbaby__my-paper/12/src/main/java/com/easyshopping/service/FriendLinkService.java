package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.entity.FriendLink;
import com.easyshopping.entity.FriendLink.Type;
public interface FriendLinkService extends BaseService<FriendLink, Long>{


public List<FriendLink> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

}