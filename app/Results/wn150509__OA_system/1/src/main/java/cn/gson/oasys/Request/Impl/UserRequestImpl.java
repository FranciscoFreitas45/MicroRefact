package cn.gson.oasys.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.DTO.User;
import cn.gson.oasys.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public User getUserId(Long userIdv2){
 User aux = restTemplate.getForObject("http://12/ProcessList/{id}/User/getUserId",User.class,userIdv2);
return aux;
}


public void setUserId(User userId,Long userIdv2){
 restTemplate.put("http://12/ProcessList/{id}/User/setUserId",userId,userIdv2);
 return ;
}


}