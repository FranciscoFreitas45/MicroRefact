package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.CapasServicioWebService;
public class CapasServicioWebServiceImpl implements CapasServicioWebService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<CapasServicioWeb> findCapasServicioWebsByServicioWeb(ServicioWeb servicioWeb){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCapasServicioWebsByServicioWeb"))
    .queryParam("servicioWeb",servicioWeb)
;  List<CapasServicioWeb> aux = restTemplate.getForObject(builder.toUriString(), List<CapasServicioWeb>.class);

 return aux;
}


}