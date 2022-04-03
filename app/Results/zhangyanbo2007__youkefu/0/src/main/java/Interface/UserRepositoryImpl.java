package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Page<User> findByOrgiAndDatastatus(String orgi,boolean b,Pageable pageRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndDatastatus"))
    .queryParam("orgi",orgi)
    .queryParam("b",b)
    .queryParam("pageRequest",pageRequest)
;  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


public List<User> findAll(Specification<User> spec){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("spec",spec)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


public List<User> findByOrgiAndAgentAndDatastatus(String orgi,boolean agent,boolean status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndAgentAndDatastatus"))
    .queryParam("orgi",orgi)
    .queryParam("agent",agent)
    .queryParam("status",status)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


public List<User> findByOrganInAndAgentAndDatastatus(List<String> organIdList,boolean b,boolean status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrganInAndAgentAndDatastatus"))
    .queryParam("organIdList",organIdList)
    .queryParam("b",b)
    .queryParam("status",status)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


public Page<User> findByOrgidAndAgentAndDatastatusAndUsertype(String orgid,boolean agent,boolean datastatus,String type,Pageable pageRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgidAndAgentAndDatastatusAndUsertype"))
    .queryParam("orgid",orgid)
    .queryParam("agent",agent)
    .queryParam("datastatus",datastatus)
    .queryParam("type",type)
    .queryParam("pageRequest",pageRequest)
;  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOne"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public User findByUsernameAndDatastatus(String username,boolean datastatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUsernameAndDatastatus"))
    .queryParam("username",username)
    .queryParam("datastatus",datastatus);
  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public User findByEmailAndDatastatus(String email,boolean datastatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmailAndDatastatus"))
    .queryParam("email",email)
    .queryParam("datastatus",datastatus);
  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public User findByMobileAndDatastatus(String mobile,boolean datastatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMobileAndDatastatus"))
    .queryParam("mobile",mobile)
    .queryParam("datastatus",datastatus);
  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}