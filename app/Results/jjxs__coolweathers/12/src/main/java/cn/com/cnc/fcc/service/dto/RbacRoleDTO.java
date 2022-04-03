package cn.com.cnc.fcc.service.dto;
 import cn.com.cnc.fcc.domain.RbacRole;
import cn.com.cnc.fcc.domain.RbacRoleRightRelation;
public class RbacRoleDTO {

 private  RbacRoleRightRelation rbacRoleRightRelation;

 private  RbacRole rbacRole;

 private  Long id;

 private  Integer rightName;

 private  Integer rightId;

 private  Integer roleId;

 private  String updateTime;


public Integer getRoleId(){
    return roleId;
}


public Long getId(){
    return id;
}


public void setRbacRoleRightRelation(RbacRoleRightRelation rbacRoleRightRelation){
    this.rbacRoleRightRelation = rbacRoleRightRelation;
}


public void setRoleId(Integer roleId){
    this.roleId = roleId;
}


public void setRbacRole(RbacRole rbacRole){
    this.rbacRole = rbacRole;
}


public String getUpdateTime(){
    return updateTime;
}


public Integer getRightName(){
    return rightName;
}


public void setRightName(Integer rightName){
    this.rightName = rightName;
}


public RbacRoleRightRelation getRbacRoleRightRelation(){
    return rbacRoleRightRelation;
}


public void setUpdateTime(String updateTime){
    this.updateTime = updateTime;
}


public void setId(Long id){
    this.id = id;
}


public void setRightId(Integer rightId){
    this.rightId = rightId;
}


public RbacRole getRbacRole(){
    return rbacRole;
}


public Integer getRightId(){
    return rightId;
}


}