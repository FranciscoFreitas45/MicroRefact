package cn.gson.oasys.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class Role {

 private  Long roleId;

 private  String roleName;

 private  Integer roleValue;

public Role() {
}public Role(String roleName, Integer roleValue) {
    super();
    this.roleName = roleName;
    this.roleValue = roleValue;
}
public String getRoleName(){
    return roleName;
}


public Integer getRoleValue(){
    return roleValue;
}


public Long getRoleId(){
    return roleId;
}


}