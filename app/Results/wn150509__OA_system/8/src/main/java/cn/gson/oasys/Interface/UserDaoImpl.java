package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.UserDao;
public class UserDaoImpl implements UserDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public User findOneUser(String userName,String password){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneUser"))
    .queryParam("userName",userName)
    .queryParam("password",password)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}