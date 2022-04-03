package cn.gson.oasys.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.DTO.User;
import cn.gson.oasys.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUser(Long userId){
 User aux = restTemplate.getForObject("http://12/Comment/{id}/User/getUser",User.class,userId);
return aux;
}


public void setUser(User user,Long userId){
 restTemplate.put("http://12/Comment/{id}/User/setUser",user,userId);
 return ;
}


}