package com.lingxiang2014.service.impl;
 import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.dao.AdPositionDao;
import com.lingxiang2014.entity.AdPosition;
import com.lingxiang2014.service.AdPositionService;
@Service("adPositionServiceImpl")
public class AdPositionServiceImpl extends BaseServiceImpl<AdPosition, Long>implements AdPositionService{

@Resource(name = "adPositionDaoImpl")
 private  AdPositionDao adPositionDao;


@Resource(name = "adPositionDaoImpl")
public void setBaseDao(AdPositionDao adPositionDao){
    super.setBaseDao(adPositionDao);
}


@Transactional(readOnly = true)
@Cacheable("adPosition")
public AdPosition find(Long id,String cacheRegion){
    return adPositionDao.find(id);
}


@Override
@Transactional
@CacheEvict(value = "adPosition", allEntries = true)
public void save(AdPosition adPosition){
    super.save(adPosition);
}


@Override
@Transactional
@CacheEvict(value = "adPosition", allEntries = true)
public AdPosition update(AdPosition adPosition,String ignoreProperties){
    return super.update(adPosition, ignoreProperties);
}


@Override
@Transactional
@CacheEvict(value = "adPosition", allEntries = true)
public void delete(AdPosition adPosition){
    super.delete(adPosition);
}


}