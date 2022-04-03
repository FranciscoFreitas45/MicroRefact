package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.MobileUser;
import org.live.Request.MobileUserRequest;
public class MobileUserRequestImpl implements MobileUserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public MobileUser getUser(String idL5CE){
 MobileUser aux = restTemplate.getForObject("http://1/AnchorLimitation/{id}/MobileUser/getUser",MobileUser.class,idL5CE);
return aux;
}


public void setUser(MobileUser user,String idL5CE){
 restTemplate.put("http://1/AnchorLimitation/{id}/MobileUser/setUser",user,idL5CE);
 return ;
}


}