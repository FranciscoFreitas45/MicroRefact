package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.UserService;
public class UserServiceImpl implements UserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void saveOrUpdate(TSUser user,String[] orgIds,String[] roleIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveOrUpdate"))
    .queryParam("user",user)
    .queryParam("orgIds",orgIds)
    .queryParam("roleIds",roleIds)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}