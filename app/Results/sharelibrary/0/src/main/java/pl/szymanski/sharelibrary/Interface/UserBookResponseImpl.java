package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.UserBookResponse;
public class UserBookResponseImpl implements UserBookResponse{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public UserBookResponse of(UserBook user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("user",user)
;  UserBookResponse aux = restTemplate.getForObject(builder.toUriString(), UserBookResponse.class);

 return aux;
}


}