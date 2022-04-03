package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class HUIdsFilterDataImpl implements HUIdsFilterData{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public HUIdsFilterData copy(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/copy"))
  HUIdsFilterData aux = restTemplate.getForObject(builder.toUriString(), HUIdsFilterData.class);

 return aux;
}


public HUIdsFilterData newEmpty(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/newEmpty"))
  HUIdsFilterData aux = restTemplate.getForObject(builder.toUriString(), HUIdsFilterData.class);

 return aux;
}


}