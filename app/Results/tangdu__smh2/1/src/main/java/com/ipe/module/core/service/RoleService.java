package com.ipe.module.core.service;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.common.service.BaseService;
import com.ipe.module.core.entity;
import com.ipe.module.core.repository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Set;
@Service
@Transactional(readOnly = true)
public class RoleService extends BaseService<Role, String>{

@Autowired
 private  RoleRepository roleRepository;

@Autowired
 private  UserRepository userRepository;

@Autowired
 private  UserRoleRepository userRoleRepository;

@Autowired
 private  AuthorityRepository authorityRepository;

@Autowired
 private  ResourceRepository resourceRepository;

@Autowired
 private  ResourceService resourceService;

@PersistenceContext
 private  EntityManager entityManager;


public void eachAuthoritys(Set<Resource> mylist,Collection<Resource> lists){
    for (Resource r1 : lists) {
        for (Resource r2 : mylist) {
            if (r2.getId().equals(r1.getId())) {
                r1.setChecked(true);
            }
        }
        if (r1.getRows() != null && !r1.getRows().isEmpty()) {
            eachAuthoritys(mylist, r1.getRows());
        }
    }
}


@Transactional(readOnly = false)
public void saveAuthority(String[] ids,String roleId){
    // 先删除
    authorityRepository.delRoleAuthority(roleId);
    /*entityManager.createQuery("delete from Authority t where t.role.id='" + roleId + "'")
                //.setParameter("roleId",roleId)
                .executeUpdate();*/
    // 新增
    Role role = roleRepository.findOne(roleId);
    if (ids != null) {
        for (String id : ids) {
            if (StringUtils.isNotBlank(id)) {
                Resource resource = resourceRepository.findOne(id);
                Authority authority = new Authority();
                authority.setResource(resource);
                authority.setRole(role);
                authorityRepository.save(authority);
            }
        }
    }
}


@Override
public CustomerRepository getRepository(){
    return roleRepository;
}


@Transactional
public void saveUserRole(String[] urids,String userId){
    // 先删除
    roleRepository.delUserRole(userId);
    // 新增
    User user = userRepository.findOne(userId);
    for (String roleId : urids) {
        if (StringUtils.isNotBlank(roleId)) {
            Role role = roleRepository.findOne(roleId);
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);
            userRoleRepository.save(userRole);
        }
    }
}


public List<Resource> getAuthoritys(String roleId){
    // 查询所有资源-树结构
    List<Resource> lists = resourceService.getResources(null);
    // 查询角色资源
    Set<Resource> mylist = authorityRepository.getRoleByAuthority(roleId);
    if (mylist == null || mylist.isEmpty()) {
        eachAuthoritys(lists);
    } else {
        // 
        eachAuthoritys(lists);
        eachAuthoritys(mylist, lists);
    }
    return lists;
}


}