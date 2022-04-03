package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.SeoDao;
import com.easyshopping.entity.Seo;
import com.easyshopping.entity.Seo.Type;
import com.easyshopping.service.SeoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("seoServiceImpl")
public class SeoServiceImpl extends BaseServiceImpl<Seo, Long>implements SeoService{

@Resource(name = "seoDaoImpl")
 private  SeoDao seoDao;


@Resource(name = "seoDaoImpl")
public void setBaseDao(SeoDao seoDao){
    super.setBaseDao(seoDao);
}


@Transactional(readOnly = true)
@Cacheable("seo")
public Seo find(Type type,String cacheRegion){
    return seoDao.find(type);
}


@Override
@Transactional
@CacheEvict(value = "seo", allEntries = true)
public void save(Seo seo){
    super.save(seo);
}


@Override
@Transactional
@CacheEvict(value = "seo", allEntries = true)
public Seo update(Seo seo,String ignoreProperties){
    return super.update(seo, ignoreProperties);
}


@Override
@Transactional
@CacheEvict(value = "seo", allEntries = true)
public void delete(Seo seo){
    super.delete(seo);
}


}