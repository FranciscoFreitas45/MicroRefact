package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.AccountID;
public class AccountIDImpl implements AccountID{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public boolean equals(Object o){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))
    .queryParam("o",o)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}