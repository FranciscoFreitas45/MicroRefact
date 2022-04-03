package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.BaseUserResponse;
public class BaseUserResponseImpl implements BaseUserResponse{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public BaseUserResponse of(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("user",user)
;  BaseUserResponse aux = restTemplate.getForObject(builder.toUriString(), BaseUserResponse.class);

 return aux;
}


}