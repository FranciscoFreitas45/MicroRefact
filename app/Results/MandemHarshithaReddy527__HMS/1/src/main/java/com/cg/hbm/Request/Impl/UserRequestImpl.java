package com.cg.hbm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.DTO.User;
import com.cg.hbm.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(int user_id){
 User aux = restTemplate.getForObject("http://0/BookingDetails/{id}/User/getUser",User.class,user_id);
return aux;
}


public void setUser(User user,int user_id){
 restTemplate.put("http://0/BookingDetails/{id}/User/setUser",user,user_id);
 return ;
}


}