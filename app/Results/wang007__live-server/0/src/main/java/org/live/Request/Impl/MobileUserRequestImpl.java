package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.MobileUser;
import org.live.Request.MobileUserRequest;
public class MobileUserRequestImpl implements MobileUserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public MobileUser getUser(String id58IK){
 MobileUser aux = restTemplate.getForObject("http://1/ApplyAnchor/{id}/MobileUser/getUser",MobileUser.class,id58IK);
return aux;
}


public void setUser(MobileUser user,String id58IK){
 restTemplate.put("http://1/ApplyAnchor/{id}/MobileUser/setUser",user,id58IK);
 return ;
}


}