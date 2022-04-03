package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.AdDao;
import com.easyshopping.entity.Ad;
import com.easyshopping.service.AdService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("adServiceImpl")
public class AdServiceImpl extends BaseServiceImpl<Ad, Long>implements AdService{


@Resource(name = "adDaoImpl")
public void setBaseDao(AdDao adDao){
    super.setBaseDao(adDao);
}


@Override
@Transactional
@CacheEvict(value = "adPosition", allEntries = true)
public void save(Ad ad){
    super.save(ad);
}


@Override
@Transactional
@CacheEvict(value = "adPosition", allEntries = true)
public Ad update(Ad ad,String ignoreProperties){
    return super.update(ad, ignoreProperties);
}


@Override
@Transactional
@CacheEvict(value = "adPosition", allEntries = true)
public void delete(Ad ad){
    super.delete(ad);
}


}