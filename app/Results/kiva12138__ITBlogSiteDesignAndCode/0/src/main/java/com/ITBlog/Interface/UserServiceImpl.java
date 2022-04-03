package com.ITBlog.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ITBlog.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public int getTypeByUserId(long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTypeByUserId"))
    .queryParam("userId",userId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int saveUser(String name,int gender,int age,String password,long phone){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveUser"))
    .queryParam("name",name)
    .queryParam("gender",gender)
    .queryParam("age",age)
    .queryParam("password",password)
    .queryParam("phone",phone)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int deleteUser(long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteUser"))
    .queryParam("userId",userId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int closeUserAccount(long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/closeUserAccount"))
    .queryParam("userId",userId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int updatePassword(long userId,String password){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updatePassword"))
    .queryParam("userId",userId)
    .queryParam("password",password)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}