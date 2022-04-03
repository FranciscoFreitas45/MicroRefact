package com.lingxiang2014.service.impl;
 import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.dao.AdDao;
import com.lingxiang2014.entity.Ad;
import com.lingxiang2014.service.AdService;
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