package gov.cdc.nccdphp.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import gov.cdc.nccdphp.Interface.DivisionRepository;
public class DivisionRepositoryImpl implements DivisionRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}