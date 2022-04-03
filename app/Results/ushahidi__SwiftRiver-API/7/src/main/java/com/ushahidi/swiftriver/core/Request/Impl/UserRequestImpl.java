package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.User;
import com.ushahidi.swiftriver.core.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getOwner(long idHR3O){
 User aux = restTemplate.getForObject("http://9/Account/{id}/User/getOwner",User.class,idHR3O);
return aux;
}


public void setOwner(User owner,long idHR3O){
 restTemplate.put("http://9/Account/{id}/User/setOwner",owner,idHR3O);
 return ;
}


}