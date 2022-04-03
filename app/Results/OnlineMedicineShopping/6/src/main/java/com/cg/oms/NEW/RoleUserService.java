package com.cg.oms.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.repository.RoleRepository;
import com.cg.oms.model.Role;
@Service
public class RoleUserService {

@Autowired
 private RoleRepository rolerepository;


public Role getRole(Long roleId){
return rolerepository.getRole(roleId);
}


public void setRole(Long roleId,Role role){
rolerepository.setRole(roleId,role);
}


}