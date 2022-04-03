package Interface;
 import DTO.MyUser;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class MyUserServiceImpl implements MyUserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public MyUser getUserById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserById"))
    .queryParam("id",id);
  MyUser aux = restTemplate.getForObject(builder.toUriString(), MyUser.class);

 return aux;
}


public MyUser getUserBySchoolcode(String schoolcode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserBySchoolcode"))
    .queryParam("schoolcode",schoolcode);
  MyUser aux = restTemplate.getForObject(builder.toUriString(), MyUser.class);

 return aux;
}


}