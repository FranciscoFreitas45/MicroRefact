package com.softserve.edu.Resources.service.impl;
 import com.softserve.edu.Resources.dao.RoleDAO;
import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class RoleService {

@Autowired
 private RoleDAO roleDAO;


public List<String> getAllRoles(){
    List<Role> list = roleDAO.getAllRoles();
    List<String> names = new ArrayList<>();
    for (Role x : list) {
        names.add(x.getName());
    }
    return names;
}


@Transactional
public List<String> getRolePrivileges(String roleName){
    Role role = roleDAO.findByName(roleName);
    List<Privilege> roles = new ArrayList<>(role.getPrivileges());
    List<String> privileges = new ArrayList<>();
    for (Privilege x : roles) {
        privileges.add(x.getName());
    }
    return privileges;
}


@Transactional
public Role updateRole(Role role){
    roleDAO.updateRole(role);
    return role;
}


@Transactional
public Role findByName(String name){
    return roleDAO.findByName(name);
}


}