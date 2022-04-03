package com.lingxiang2014.service.impl;
 import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.Principal;
import com.lingxiang2014.dao.AdminDao;
import com.lingxiang2014.entity.Admin;
import com.lingxiang2014.entity.Role;
import com.lingxiang2014.service.AdminService;
@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long>implements AdminService{

@Resource(name = "adminDaoImpl")
 private  AdminDao adminDao;


@Transactional(readOnly = true)
public boolean usernameExists(String username){
    return adminDao.usernameExists(username);
}


@Transactional(readOnly = true)
public Admin findByUsername(String username){
    return adminDao.findByUsername(username);
}


@Resource(name = "adminDaoImpl")
public void setBaseDao(AdminDao adminDao){
    super.setBaseDao(adminDao);
}


@Transactional(readOnly = true)
public String getCurrentUsername(){
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
        Principal principal = (Principal) subject.getPrincipal();
        if (principal != null) {
            return principal.getUsername();
        }
    }
    return null;
}


@Transactional(readOnly = true)
public List<String> findAuthorities(Long id){
    List<String> authorities = new ArrayList<String>();
    Admin admin = adminDao.find(id);
    if (admin != null) {
        for (Role role : admin.getRoles()) {
            authorities.addAll(role.getAuthorities());
        }
    }
    return authorities;
}


@Override
@Transactional
@CacheEvict(value = "authorization", allEntries = true)
public void save(Admin admin){
    super.save(admin);
}


@Override
@Transactional
@CacheEvict(value = "authorization", allEntries = true)
public Admin update(Admin admin,String ignoreProperties){
    return super.update(admin, ignoreProperties);
}


@Transactional(readOnly = true)
public boolean isAuthenticated(){
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
        return subject.isAuthenticated();
    }
    return false;
}


@Transactional(readOnly = true)
public Admin getCurrent(){
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
        Principal principal = (Principal) subject.getPrincipal();
        if (principal != null) {
            return adminDao.find(principal.getId());
        }
    }
    return null;
}


@Override
@Transactional
@CacheEvict(value = "authorization", allEntries = true)
public void delete(Admin admin){
    super.delete(admin);
}


}