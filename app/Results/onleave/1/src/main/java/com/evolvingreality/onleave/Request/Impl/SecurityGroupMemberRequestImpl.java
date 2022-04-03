package com.evolvingreality.onleave.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.DTO.SecurityGroupMember;
import com.evolvingreality.onleave.Request.SecurityGroupMemberRequest;
public class SecurityGroupMemberRequestImpl implements SecurityGroupMemberRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setGroupMembers(List<SecurityGroupMember> groupMembers,Long id){
 restTemplate.put("http://4/User/{id}/SecurityGroupMember/setGroupMembers",groupMembers,id);
 return ;
}


public List<SecurityGroupMember> getGroupMembers(Long id){
 List<SecurityGroupMember> aux = restTemplate.getForObject("http://4/User/{id}/SecurityGroupMember/getGroupMembers",List<SecurityGroupMember>.class,id);
return aux;
}


}