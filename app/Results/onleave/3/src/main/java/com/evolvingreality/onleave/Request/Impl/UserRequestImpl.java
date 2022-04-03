package com.evolvingreality.onleave.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.DTO.User;
import com.evolvingreality.onleave.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(Long id){
 User aux = restTemplate.getForObject("http://1/Leave/{id}/User/getUser",User.class,id);
return aux;
}


public void setUser(User user,Long id){
 restTemplate.put("http://1/Leave/{id}/User/setUser",user,id);
 return ;
}


}