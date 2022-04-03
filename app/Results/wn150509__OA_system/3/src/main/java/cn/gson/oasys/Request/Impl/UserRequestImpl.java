package cn.gson.oasys.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.DTO.User;
import cn.gson.oasys.Request.UserRequest;
public class UserRequestImpl implements UserRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setMailUserid(User mailUserid,Long userId){
 restTemplate.put("http://12/Inmaillist/{id}/User/setMailUserid",mailUserid,userId);
 return ;
}


public User getMailUserid(Long userId){
 User aux = restTemplate.getForObject("http://12/Inmaillist/{id}/User/getMailUserid",User.class,userId);
return aux;
}


}