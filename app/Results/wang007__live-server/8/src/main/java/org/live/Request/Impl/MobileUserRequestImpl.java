package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.MobileUser;
import org.live.Request.MobileUserRequest;
public class MobileUserRequestImpl implements MobileUserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public MobileUser getUser(String idYUDA){
 MobileUser aux = restTemplate.getForObject("http://1/Report/{id}/MobileUser/getUser",MobileUser.class,idYUDA);
return aux;
}


public void setUser(MobileUser user,String idYUDA){
 restTemplate.put("http://1/Report/{id}/MobileUser/setUser",user,idYUDA);
 return ;
}


}