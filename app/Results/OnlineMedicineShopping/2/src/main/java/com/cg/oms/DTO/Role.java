package com.cg.oms.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
public class Role {

 private  Long roleId;

 private  String roleName;

public Role() {
    super();
}/**
 * getters and setters of entity table
 *
 * @param roleName
 */
public Role(String roleName) {
    super();
    this.roleName = roleName;
}
public String getRoleName(){
    return roleName;
}


public Long getRoleId(){
    return roleId;
}

public void setRoleId(long roleId){
    this.roleId = roleId;
}


}