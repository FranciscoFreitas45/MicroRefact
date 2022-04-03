package com.lingxiang2014.service;
 import java.util.List;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.entity.FriendLink;
import com.lingxiang2014.entity.FriendLink.Type;
public interface FriendLinkService extends BaseService<FriendLink, Long>{


public List<FriendLink> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

}