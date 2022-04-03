package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.GroupRole;
import com.hmm.Request.GroupRoleRequest;
public class GroupRoleRequestImpl implements GroupRoleRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<GroupRole> getGroupRoles(Integer dept_id){
 List<GroupRole> aux = restTemplate.getForObject("http://16/Department/{id}/GroupRole/getGroupRoles",List<GroupRole>.class,dept_id);
return aux;
}


public void setGroupRoles(List<GroupRole> groupRoles,Integer dept_id){
 restTemplate.put("http://16/Department/{id}/GroupRole/setGroupRoles",groupRoles,dept_id);
 return ;
}


}