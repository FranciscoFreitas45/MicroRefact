package com.lingxiang2014.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.dao.TagDao;
import com.lingxiang2014.entity.Tag;
import com.lingxiang2014.entity.Tag.Type;
import com.lingxiang2014.service.TagService;
@Service("tagServiceImpl")
public class TagServiceImpl extends BaseServiceImpl<Tag, Long>implements TagService{

@Resource(name = "tagDaoImpl")
 private  TagDao tagDao;


@Resource(name = "tagDaoImpl")
public void setBaseDao(TagDao tagDao){
    super.setBaseDao(tagDao);
}


@Transactional(readOnly = true)
@Cacheable("tag")
public List<Tag> findList(Integer count,List<Filter> filters,List<Order> orders,String cacheRegion){
    return tagDao.findList(null, count, filters, orders);
}


@Override
@Transactional
@CacheEvict(value = "tag", allEntries = true)
public void save(Tag tag){
    super.save(tag);
}


@Override
@Transactional
@CacheEvict(value = "tag", allEntries = true)
public Tag update(Tag tag,String ignoreProperties){
    return super.update(tag, ignoreProperties);
}


@Override
@Transactional
@CacheEvict(value = "tag", allEntries = true)
public void delete(Tag tag){
    super.delete(tag);
}


}