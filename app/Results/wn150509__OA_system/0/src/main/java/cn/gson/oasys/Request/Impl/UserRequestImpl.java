package cn.gson.oasys.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.DTO.User;
import cn.gson.oasys.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setMyuser(User myuser,Long userId){
 restTemplate.put("http://12/Director/{id}/User/setMyuser",myuser,userId);
 return ;
}


public User getMyuser(Long userId){
 User aux = restTemplate.getForObject("http://12/Director/{id}/User/getMyuser",User.class,userId);
return aux;
}


}