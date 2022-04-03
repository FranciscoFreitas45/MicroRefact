package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.ConfVistasPredefinidasService;
public class ConfVistasPredefinidasServiceImpl implements ConfVistasPredefinidasService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object findConfVistasPredefinidas(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findConfVistasPredefinidas"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object saveConfVistasPredefinidas(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveConfVistasPredefinidas"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<ConfVistasPredefinidas> findConfVistasPredefinidasesByGeoPortal(GeoPortal geoPortal){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findConfVistasPredefinidasesByGeoPortal"))
    .queryParam("geoPortal",geoPortal)
;  List<ConfVistasPredefinidas> aux = restTemplate.getForObject(builder.toUriString(), List<ConfVistasPredefinidas>.class);

 return aux;
}


public Object deleteConfVistasPredefinidas(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteConfVistasPredefinidas"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}