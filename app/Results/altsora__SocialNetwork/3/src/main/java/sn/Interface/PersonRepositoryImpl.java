package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.PersonRepository;
public class PersonRepositoryImpl implements PersonRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object existsById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existsById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Optional<Person> findByEmail(String email){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmail"))
    .queryParam("email",email)
;  Optional<Person> aux = restTemplate.getForObject(builder.toUriString(), Optional<Person>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object deleteById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public int getTotalCountUsers(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTotalCountUsers"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}