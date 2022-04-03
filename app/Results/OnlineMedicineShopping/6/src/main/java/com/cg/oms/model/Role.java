package com.cg.oms.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
@Entity
@Component
public class Role {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
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
public void setRoleId(Long roleId){
    this.roleId = roleId;
}


public String getRoleName(){
    return roleName;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public Long getRoleId(){
    return roleId;
}


@Override
public String toString(){
    return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
}


}