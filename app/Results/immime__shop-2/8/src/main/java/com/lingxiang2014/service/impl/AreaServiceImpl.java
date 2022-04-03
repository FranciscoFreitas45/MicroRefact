package com.lingxiang2014.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.dao.AreaDao;
import com.lingxiang2014.entity.Area;
import com.lingxiang2014.service.AreaService;
@Service("areaServiceImpl")
public class AreaServiceImpl extends BaseServiceImpl<Area, Long>implements AreaService{

@Resource(name = "areaDaoImpl")
 private  AreaDao areaDao;


@Transactional(readOnly = true)
public List<Area> findRoots(Integer count){
    return areaDao.findRoots(count);
}


@Resource(name = "areaDaoImpl")
public void setBaseDao(AreaDao areaDao){
    super.setBaseDao(areaDao);
}


@Override
@Transactional
@CacheEvict(value = "area", allEntries = true)
public void save(Area area){
    super.save(area);
}


@Override
@Transactional
@CacheEvict(value = "area", allEntries = true)
public Area update(Area area,String ignoreProperties){
    return super.update(area, ignoreProperties);
}


@Override
@Transactional
@CacheEvict(value = "area", allEntries = true)
public void delete(Area area){
    super.delete(area);
}


}