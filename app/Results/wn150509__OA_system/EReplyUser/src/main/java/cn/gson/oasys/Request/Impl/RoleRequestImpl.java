package cn.gson.oasys.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.DTO.Role;
import cn.gson.oasys.Request.RoleRequest;
public class RoleRequestImpl implements RoleRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setRole(Role role,Long roleId){
 restTemplate.put("http://7/User/{id}/Role/setRole",role,roleId);
 return ;
}


public Role getRole(Long roleId){
 Role aux = restTemplate.getForObject("http://7/User/{id}/Role/getRole",Role.class,roleId);
return aux;
}


}