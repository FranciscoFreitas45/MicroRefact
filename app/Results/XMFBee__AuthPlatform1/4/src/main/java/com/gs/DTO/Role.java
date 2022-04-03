package com.gs.DTO;
 public class Role {

 private  String roleId;

 private  String roleName;

 private  String roleDes;

 private  String roleStatus;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://17";


public String getRoleName(){
    return roleName;
}


public String getRoleStatus(){
    return roleStatus;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public String getRoleDes(){
    return roleDes;
}


public String getRoleId(){
    return roleId;
}


public void setRoleStatus(String roleStatus){
    this.roleStatus = roleStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRoleStatus"))

.queryParam("roleStatus",roleStatus)
;
restTemplate.put(builder.toUriString(),null);
}


}