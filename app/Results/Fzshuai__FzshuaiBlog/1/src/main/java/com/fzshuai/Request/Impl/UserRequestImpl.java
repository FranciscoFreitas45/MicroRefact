package com.fzshuai.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.DTO.User;
import com.fzshuai.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(Long id){
 User aux = restTemplate.getForObject("http://4/Blog/{id}/User/getUser",User.class,id);
return aux;
}


public void setUser(User user,Long id){
 restTemplate.put("http://4/Blog/{id}/User/setUser",user,id);
 return ;
}


}