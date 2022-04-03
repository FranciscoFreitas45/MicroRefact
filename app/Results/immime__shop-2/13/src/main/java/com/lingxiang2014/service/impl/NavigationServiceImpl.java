package com.lingxiang2014.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.dao.NavigationDao;
import com.lingxiang2014.entity.Navigation;
import com.lingxiang2014.entity.Navigation.Position;
import com.lingxiang2014.service.NavigationService;
@Service("navigationServiceImpl")
public class NavigationServiceImpl extends BaseServiceImpl<Navigation, Long>implements NavigationService{

@Resource(name = "navigationDaoImpl")
 private  NavigationDao navigationDao;


@Resource(name = "navigationDaoImpl")
public void setBaseDao(NavigationDao navigationDao){
    super.setBaseDao(navigationDao);
}


@Transactional(readOnly = true)
@Cacheable("navigation")
public List<Navigation> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion){
    return navigationDao.findList(null, count, filters, orders);
}


@Override
@Transactional
@CacheEvict(value = "navigation", allEntries = true)
public void save(Navigation navigation){
    super.save(navigation);
}


@Override
@Transactional
@CacheEvict(value = "navigation", allEntries = true)
public Navigation update(Navigation navigation,String ignoreProperties){
    return super.update(navigation, ignoreProperties);
}


@Override
@Transactional
@CacheEvict(value = "navigation", allEntries = true)
public void delete(Navigation navigation){
    super.delete(navigation);
}


}