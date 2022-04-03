package com.lingxiang2014.service;
 import java.util.List;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.entity.Tag;
import com.lingxiang2014.entity.Tag.Type;
public interface TagService extends BaseService<Tag, Long>{


public List<Tag> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

}