package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.UserLogDao;
public class UserLogDaoImpl implements UserLogDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<UserLog> findByUser(long userid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUser"))
    .queryParam("userid",userid)
;  List<UserLog> aux = restTemplate.getForObject(builder.toUriString(), List<UserLog>.class);

 return aux;
}


}