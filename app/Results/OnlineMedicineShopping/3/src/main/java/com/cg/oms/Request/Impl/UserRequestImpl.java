package com.cg.oms.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.DTO.User;
import com.cg.oms.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(Long userId){
 User aux = restTemplate.getForObject("http://2/Order/{id}/User/getUser",User.class,userId);
return aux;
}


public void setUser(User user,Long userId){
 restTemplate.put("http://2/Order/{id}/User/setUser",user,userId);
 return ;
}


}