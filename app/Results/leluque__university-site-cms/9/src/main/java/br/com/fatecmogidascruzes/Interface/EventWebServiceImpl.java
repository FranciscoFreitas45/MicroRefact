package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.EventWebService;
public class EventWebServiceImpl implements EventWebService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<EventHomeDTO> getHomeEvents(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHomeEvents"))
;  List<EventHomeDTO> aux = restTemplate.getForObject(builder.toUriString(), List<EventHomeDTO>.class);

 return aux;
}


public Event abrirLink(UUID hash){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/abrirLink"))
    .queryParam("hash",hash)
;  Event aux = restTemplate.getForObject(builder.toUriString(), Event.class);

 return aux;
}


public EventDetailDTO getEventDetail(UUID hash){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEventDetail"))
    .queryParam("hash",hash)
;  EventDetailDTO aux = restTemplate.getForObject(builder.toUriString(), EventDetailDTO.class);

 return aux;
}


public List<EventHomeDTO> getEnabledEvents(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEnabledEvents"))
;  List<EventHomeDTO> aux = restTemplate.getForObject(builder.toUriString(), List<EventHomeDTO>.class);

 return aux;
}


}