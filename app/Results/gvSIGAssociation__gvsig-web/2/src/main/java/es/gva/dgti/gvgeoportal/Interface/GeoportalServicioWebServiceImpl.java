package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.GeoportalServicioWebService;
public class GeoportalServicioWebServiceImpl implements GeoportalServicioWebService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<GeoportalServicioWeb> findGeoportalServicioWebsByGeoportal(GeoPortal geoPortal){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findGeoportalServicioWebsByGeoportal"))
    .queryParam("geoPortal",geoPortal)
;  List<GeoportalServicioWeb> aux = restTemplate.getForObject(builder.toUriString(), List<GeoportalServicioWeb>.class);

 return aux;
}


public Object saveGeoportalServicioWeb(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveGeoportalServicioWeb"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}