package com.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.Interface.LoginService;
public class LoginServiceImpl implements LoginService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Login find(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("id",id)
;  Login aux = restTemplate.getForObject(builder.toUriString(), Login.class);

 return aux;
}


public void delet(Login login){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delet"))
    .queryParam("login",login)
;
  restTemplate.put(builder.toUriString(), null);
}


public void update(Login login){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("login",login)
;
  restTemplate.put(builder.toUriString(), null);
}


}