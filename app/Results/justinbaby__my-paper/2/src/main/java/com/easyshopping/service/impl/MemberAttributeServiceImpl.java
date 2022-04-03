package com.easyshopping.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import com.easyshopping.dao.MemberAttributeDao;
import com.easyshopping.entity.MemberAttribute;
import com.easyshopping.service.MemberAttributeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("memberAttributeServiceImpl")
public class MemberAttributeServiceImpl extends BaseServiceImpl<MemberAttribute, Long>implements MemberAttributeService{

@Resource(name = "memberAttributeDaoImpl")
 private  MemberAttributeDao memberAttributeDao;


@Resource(name = "memberAttributeDaoImpl")
public void setBaseDao(MemberAttributeDao memberAttributeDao){
    super.setBaseDao(memberAttributeDao);
}


@Transactional(readOnly = true)
@Cacheable("memberAttribute")
public List<MemberAttribute> findList(String cacheRegion){
    return memberAttributeDao.findList();
}


@Override
@Transactional
@CacheEvict(value = "memberAttribute", allEntries = true)
public void save(MemberAttribute memberAttribute){
    super.save(memberAttribute);
}


@Override
@Transactional
@CacheEvict(value = "memberAttribute", allEntries = true)
public MemberAttribute update(MemberAttribute memberAttribute,String ignoreProperties){
    return super.update(memberAttribute, ignoreProperties);
}


@Override
@Transactional
@CacheEvict(value = "memberAttribute", allEntries = true)
public void delete(MemberAttribute memberAttribute){
    super.delete(memberAttribute);
}


@Transactional(readOnly = true)
public Integer findUnusedPropertyIndex(){
    return memberAttributeDao.findUnusedPropertyIndex();
}


}