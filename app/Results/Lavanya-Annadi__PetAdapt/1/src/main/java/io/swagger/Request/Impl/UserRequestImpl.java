package io.swagger.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.DTO.User;
import io.swagger.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(Long id){
 User aux = restTemplate.getForObject("http://2/Order/{id}/User/getUser",User.class,id);
return aux;
}


public void setUser(User user,Long id){
 restTemplate.put("http://2/Order/{id}/User/setUser",user,id);
 return ;
}


}