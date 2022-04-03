package com.yalcin.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.DTO.User;
import com.yalcin.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(Integer id){
 User aux = restTemplate.getForObject("http://10/SellerBegin/{id}/User/getUser",User.class,id);
return aux;
}


public void setUser(User user,Integer id){
 restTemplate.put("http://10/SellerBegin/{id}/User/setUser",user,id);
 return ;
}


}