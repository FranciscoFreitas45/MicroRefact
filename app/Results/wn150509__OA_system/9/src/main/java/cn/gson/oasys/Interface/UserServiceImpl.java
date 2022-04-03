package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<User> findmyemployuser(int page,String baseKey,long parentid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findmyemployuser"))
    .queryParam("page",page)
    .queryParam("baseKey",baseKey)
    .queryParam("parentid",parentid)
;  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


}