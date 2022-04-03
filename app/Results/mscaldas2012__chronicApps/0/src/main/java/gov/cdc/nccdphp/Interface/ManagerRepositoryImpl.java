package gov.cdc.nccdphp.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import gov.cdc.nccdphp.Interface.ManagerRepository;
public class ManagerRepositoryImpl implements ManagerRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Manager> findByActiveTrue(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByActiveTrue"))
;  List<Manager> aux = restTemplate.getForObject(builder.toUriString(), List<Manager>.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}