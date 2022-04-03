package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapaService;
public class AgrupadorCapaServiceImpl implements AgrupadorCapaService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object findAgrupadorCapa(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAgrupadorCapa"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}