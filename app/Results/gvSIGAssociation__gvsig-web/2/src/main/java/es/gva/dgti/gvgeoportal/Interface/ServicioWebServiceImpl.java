package es.gva.dgti.gvgeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
public class ServicioWebServiceImpl implements ServicioWebService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Map<String,String> getLayersAndStylesOrderByLayersName(ServicioWeb servicioWeb){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLayersAndStylesOrderByLayersName"))
    .queryParam("servicioWeb",servicioWeb)
;  Map<String,String> aux = restTemplate.getForObject(builder.toUriString(), Map<String,String>.class);

 return aux;
}


}