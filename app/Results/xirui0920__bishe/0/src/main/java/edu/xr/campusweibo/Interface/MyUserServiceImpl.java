package edu.xr.campusweibo.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.Interface.MyUserService;
public class MyUserServiceImpl implements MyUserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public MyUser getUserById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserById"))
    .queryParam("id",id)
;  MyUser aux = restTemplate.getForObject(builder.toUriString(), MyUser.class);

 return aux;
}


}