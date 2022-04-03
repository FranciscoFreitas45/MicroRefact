package com.softserve.edu.Resources.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.DTO.User;
import com.softserve.edu.Resources.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(Long idO2KS){
 User aux = restTemplate.getForObject("http://9/UserDetails/{id}/User/getUser",User.class,idO2KS);
return aux;
}


public void setUser(User user,Long idO2KS){
 restTemplate.put("http://9/UserDetails/{id}/User/setUser",user,idO2KS);
 return ;
}


}