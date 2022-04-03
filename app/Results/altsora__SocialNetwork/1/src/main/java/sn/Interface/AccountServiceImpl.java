package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.AccountService;
public class AccountServiceImpl implements AccountService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public PersonResponse getPersonResponse(Person person){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getPersonResponse"))
    .queryParam("person",person)
;  PersonResponse aux = restTemplate.getForObject(builder.toUriString(), PersonResponse.class);

 return aux;
}


}