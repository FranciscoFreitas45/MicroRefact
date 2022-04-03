package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.AccountService;
public class AccountServiceImpl implements AccountService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Person findCurrentUser(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCurrentUser"))
;  Person aux = restTemplate.getForObject(builder.toUriString(), Person.class);

 return aux;
}


public boolean exists(long personId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/exists"))
    .queryParam("personId",personId)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}