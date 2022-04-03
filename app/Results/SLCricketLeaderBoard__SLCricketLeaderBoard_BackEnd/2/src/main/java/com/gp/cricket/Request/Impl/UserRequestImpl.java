package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.User;
import com.gp.cricket.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUserId(Integer userIdv2){
 User aux = restTemplate.getForObject("http://5/Referee/{id}/User/getUserId",User.class,userIdv2);
return aux;
}


public void setUserId(User userId,Integer userIdv2){
 restTemplate.put("http://5/Referee/{id}/User/setUserId",userId,userIdv2);
 return ;
}


}