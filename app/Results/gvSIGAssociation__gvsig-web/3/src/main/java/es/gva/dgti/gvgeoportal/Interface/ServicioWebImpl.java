package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.ServicioWeb;
public class ServicioWebImpl implements ServicioWeb{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object getNombre(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNombre"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}