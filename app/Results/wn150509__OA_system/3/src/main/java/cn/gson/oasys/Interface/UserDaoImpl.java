package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.UserDao;
public class UserDaoImpl implements UserDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public User findid(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findid"))
    .queryParam("name",name)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public Page<User> findbyUserNameLike(String name,Pageable pa){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findbyUserNameLike"))
    .queryParam("name",name)
    .queryParam("pa",pa)
;  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Page<User> findbyFatherId(String name,Long parentid,Pageable pa){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findbyFatherId"))
    .queryParam("name",name)
    .queryParam("parentid",parentid)
    .queryParam("pa",pa)
;  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


public Page<User> findByFatherId(Long parentid,Pageable pa){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByFatherId"))
    .queryParam("parentid",parentid)
    .queryParam("pa",pa)
;  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


}