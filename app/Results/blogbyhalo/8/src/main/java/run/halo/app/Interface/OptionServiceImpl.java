package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.OptionService;
public class OptionServiceImpl implements OptionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public T getByPropertyOrDefault(PropertyEnum property,Class<T> propertyType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByPropertyOrDefault"))
    .queryParam("property",property)
    .queryParam("propertyType",propertyType)
;  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


public String getSeoKeywords(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSeoKeywords"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getSeoDescription(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSeoDescription"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}