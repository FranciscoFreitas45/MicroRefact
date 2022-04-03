package edu.xr.campusweibo.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.DTO.User;
import edu.xr.campusweibo.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(Long id){
 User aux = restTemplate.getForObject("http://4/PersistentToken/{id}/User/getUser",User.class,id);
return aux;
}


public void setUser(User user,Long id){
 restTemplate.put("http://4/PersistentToken/{id}/User/setUser",user,id);
 return ;
}


}