package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.UserDAO;
public class UserDAOImpl implements UserDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public User findOneById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneById"))
    .queryParam("id",id)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public User findOneByNomAndPrenom(String nom,String prenom){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneByNomAndPrenom"))
    .queryParam("nom",nom)
    .queryParam("prenom",prenom)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public User findOneByPseudo(String pseudo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneByPseudo"))
    .queryParam("pseudo",pseudo)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public long count(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object exists(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/exists"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public User findOneByEmail(String email){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneByEmail"))
    .queryParam("email",email)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


}