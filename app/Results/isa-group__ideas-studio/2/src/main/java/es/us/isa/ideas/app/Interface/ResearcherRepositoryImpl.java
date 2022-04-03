package es.us.isa.ideas.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.us.isa.ideas.app.Interface.ResearcherRepository;
public class ResearcherRepositoryImpl implements ResearcherRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Researcher findByUserAccountId(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserAccountId"))
    .queryParam("id",id)
;  Researcher aux = restTemplate.getForObject(builder.toUriString(), Researcher.class);

 return aux;
}


public Researcher findByUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUsername"))
    .queryParam("username",username)
;  Researcher aux = restTemplate.getForObject(builder.toUriString(), Researcher.class);

 return aux;
}


public Researcher findByEmail(String email){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmail"))
    .queryParam("email",email)
;  Researcher aux = restTemplate.getForObject(builder.toUriString(), Researcher.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}