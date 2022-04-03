package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<User> findAll(Specification<User> spec){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("spec",spec)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


public List<User> findByOrgi(String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgi"))
    .queryParam("orgi",orgi);
  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


public Page<User> findByDatastatusAndOrgi(boolean datastatus,String orgi,Pageable paramPageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDatastatusAndOrgi"))
    .queryParam("datastatus",datastatus)
    .queryParam("orgi",orgi)
    .queryParam("paramPageable",paramPageable);
  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


public Page<User> findByDatastatusAndOrgiAndUsernameLike(boolean datastatus,String orgi,String username,Pageable paramPageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDatastatusAndOrgiAndUsernameLike"))
    .queryParam("datastatus",datastatus)
    .queryParam("orgi",orgi)
    .queryParam("username",username)
    .queryParam("paramPageable",paramPageable);
  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


public Page<User> findByIdAndOrgi(String id,String orgi,Pageable paramPageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndOrgi"))
    .queryParam("id",id)
    .queryParam("orgi",orgi)
    .queryParam("paramPageable",paramPageable);
  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<User> findByOrgiAndAgentAndDatastatus(String orgi,boolean agent,boolean status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndAgentAndDatastatus"))
    .queryParam("orgi",orgi)
    .queryParam("agent",agent)
    .queryParam("status",status);
  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


public List<User> findByOrganInAndAgentAndDatastatus(List<String> organIdList,boolean b,boolean status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrganInAndAgentAndDatastatus"))
    .queryParam("organIdList",organIdList)
    .queryParam("b",b)
    .queryParam("status",status);
  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


}