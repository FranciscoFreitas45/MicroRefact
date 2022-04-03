package com.lingxiang2014.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.dao.FriendLinkDao;
import com.lingxiang2014.entity.FriendLink;
import com.lingxiang2014.entity.FriendLink.Type;
import com.lingxiang2014.service.FriendLinkService;
@Service("friendLinkServiceImpl")
public class FriendLinkServiceImpl extends BaseServiceImpl<FriendLink, Long>implements FriendLinkService{

@Resource(name = "friendLinkDaoImpl")
 public  FriendLinkDao friendLinkDao;


@Resource(name = "friendLinkDaoImpl")
public void setBaseDao(FriendLinkDao friendLinkDao){
    super.setBaseDao(friendLinkDao);
}


@Transactional(readOnly = true)
@Cacheable("friendLink")
public List<FriendLink> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion){
    return friendLinkDao.findList(null, count, filters, orders);
}


@Override
@Transactional
@CacheEvict(value = "friendLink", allEntries = true)
public void save(FriendLink friendLink){
    super.save(friendLink);
}


@Override
@Transactional
@CacheEvict(value = "friendLink", allEntries = true)
public FriendLink update(FriendLink friendLink,String ignoreProperties){
    return super.update(friendLink, ignoreProperties);
}


@Override
@Transactional
@CacheEvict(value = "friendLink", allEntries = true)
public void delete(FriendLink friendLink){
    super.delete(friendLink);
}


}