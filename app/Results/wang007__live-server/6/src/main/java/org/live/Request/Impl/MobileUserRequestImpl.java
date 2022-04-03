package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.MobileUser;
import org.live.Request.MobileUserRequest;
public class MobileUserRequestImpl implements MobileUserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public MobileUser getUser(String idQ2RW){
 MobileUser aux = restTemplate.getForObject("http://1/Anchor/{id}/MobileUser/getUser",MobileUser.class,idQ2RW);
return aux;
}


public void setUser(MobileUser user,String idQ2RW){
 restTemplate.put("http://1/Anchor/{id}/MobileUser/setUser",user,idQ2RW);
 return ;
}


}