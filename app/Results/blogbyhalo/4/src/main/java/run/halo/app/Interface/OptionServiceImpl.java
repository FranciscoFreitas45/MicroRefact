package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.OptionService;
public class OptionServiceImpl implements OptionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public String getBlogBaseUrl(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBlogBaseUrl"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Boolean isEnabledAbsolutePath(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEnabledAbsolutePath"))
;  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


public T getEnumByPropertyOrDefault(PropertyEnum property,Class<T> valueType,T defaultValue){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEnumByPropertyOrDefault"))
    .queryParam("property",property)
    .queryParam("valueType",valueType)
    .queryParam("defaultValue",defaultValue)
;  T aux = restTemplate.getForObject(builder.toUriString(), T.class);

 return aux;
}


}