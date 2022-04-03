package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
public class ServicioWebServiceImpl implements ServicioWebService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public TypedQuery<ServicioWeb> findServicesNotInIdListAndNoGroup(List<Long> idList,AgrupadorCapa agrupadorCapa){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findServicesNotInIdListAndNoGroup"))
    .queryParam("idList",idList)
    .queryParam("agrupadorCapa",agrupadorCapa)
;  TypedQuery<ServicioWeb> aux = restTemplate.getForObject(builder.toUriString(), TypedQuery<ServicioWeb>.class);

 return aux;
}


public TypedQuery<ServicioWeb> findServicesInIdList(List<Long> idList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findServicesInIdList"))
    .queryParam("idList",idList)
;  TypedQuery<ServicioWeb> aux = restTemplate.getForObject(builder.toUriString(), TypedQuery<ServicioWeb>.class);

 return aux;
}


public TypedQuery<ServicioWeb> findServicesByNoGroup(AgrupadorCapa agrupadorCapa){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findServicesByNoGroup"))
    .queryParam("agrupadorCapa",agrupadorCapa)
;  TypedQuery<ServicioWeb> aux = restTemplate.getForObject(builder.toUriString(), TypedQuery<ServicioWeb>.class);

 return aux;
}


public Object getResultList(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getResultList"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}