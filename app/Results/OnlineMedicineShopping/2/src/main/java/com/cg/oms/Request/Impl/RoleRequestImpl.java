package com.cg.oms.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.DTO.Role;
import com.cg.oms.Request.RoleRequest;
public class RoleRequestImpl implements RoleRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Role getRole(Long roleId){
 Role aux = restTemplate.getForObject("http://localhost:8086/User/{id}/Role/getRole",Role.class,roleId);
return aux;
}


public void setRole(Role role,Long roleId){
 restTemplate.put("http://localhost:8086/User/{id}/Role/setRole",role,roleId);
 return ;
}


}