package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.RoleDao;
import com.easyshopping.entity.Role;
import com.easyshopping.service.RoleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long>implements RoleService{


@Resource(name = "roleDaoImpl")
public void setBaseDao(RoleDao roleDao){
    super.setBaseDao(roleDao);
}


@Override
@Transactional
@CacheEvict(value = "authorization", allEntries = true)
public void save(Role role){
    super.save(role);
}


@Override
@Transactional
@CacheEvict(value = "authorization", allEntries = true)
public Role update(Role role,String ignoreProperties){
    return super.update(role, ignoreProperties);
}


@Override
@Transactional
@CacheEvict(value = "authorization", allEntries = true)
public void delete(Role role){
    super.delete(role);
}


}