package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapaServicioWebService;
public class AgrupadorCapaServicioWebServiceImpl implements AgrupadorCapaServicioWebService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public TypedQuery<Long> findServicesByGroup(Long agrupadorCapaId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findServicesByGroup"))
    .queryParam("agrupadorCapaId",agrupadorCapaId)
;  TypedQuery<Long> aux = restTemplate.getForObject(builder.toUriString(), TypedQuery<Long>.class);

 return aux;
}


public Object findAgrupadorCapaServicioWeb(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAgrupadorCapaServicioWeb"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}