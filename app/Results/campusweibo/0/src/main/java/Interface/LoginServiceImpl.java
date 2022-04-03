package Interface;
 import DTO.MyUser;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class LoginServiceImpl implements LoginService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public MyUser findBySchoolcodeAndPassword(String schoolcode, String password){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySchoolcodeAndPassword"))
    .queryParam("schoolcode",schoolcode)
    .queryParam("password",password);
  MyUser aux = restTemplate.getForObject(builder.toUriString(), MyUser.class);

 return aux;
}


public MyUser getUserById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserById"))
    .queryParam("id",id);
  MyUser aux = restTemplate.getForObject(builder.toUriString(), MyUser.class);

 return aux;
}


}