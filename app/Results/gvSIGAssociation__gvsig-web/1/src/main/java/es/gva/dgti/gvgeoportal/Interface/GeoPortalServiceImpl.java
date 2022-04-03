package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.GeoPortalService;
public class GeoPortalServiceImpl implements GeoPortalService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public TypedQuery<GeoPortal> findPublicGeoPortalesByUrlEquals(String url){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPublicGeoPortalesByUrlEquals"))
    .queryParam("url",url)
;  TypedQuery<GeoPortal> aux = restTemplate.getForObject(builder.toUriString(), TypedQuery<GeoPortal>.class);

 return aux;
}


public TypedQuery<GeoPortal> findGeoPortalesByUrlEquals(String url,String sortFieldName,String sortOrder){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findGeoPortalesByUrlEquals"))
    .queryParam("url",url)
    .queryParam("sortFieldName",sortFieldName)
    .queryParam("sortOrder",sortOrder)
;  TypedQuery<GeoPortal> aux = restTemplate.getForObject(builder.toUriString(), TypedQuery<GeoPortal>.class);

 return aux;
}


public Map<String,Object> getComponentsAndInformationByGeoportal(GeoPortal geoportal){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getComponentsAndInformationByGeoportal"))
    .queryParam("geoportal",geoportal)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


}