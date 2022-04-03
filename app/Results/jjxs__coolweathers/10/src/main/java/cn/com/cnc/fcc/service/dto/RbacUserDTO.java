package cn.com.cnc.fcc.service.dto;
 public class RbacUserDTO {

 private  String roleId;

 private  String roleName;

 private  String updateTime;

 private  Long id;


public void setRoleId(String roleId){
    this.roleId = roleId;
}


public String getUpdateTime(){
    return updateTime;
}


public String getRoleName(){
    return roleName;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public void setId(Long id){
    this.id = id;
}


public void setUpdateTime(String updateTime){
    this.updateTime = updateTime;
}


public String getRoleId(){
    return roleId;
}


public Long getId(){
    return id;
}


}