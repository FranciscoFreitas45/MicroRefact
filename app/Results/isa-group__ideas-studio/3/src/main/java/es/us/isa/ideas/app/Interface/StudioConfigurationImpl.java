package es.us.isa.ideas.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import es.us.isa.ideas.app.Interface.StudioConfiguration;
public class StudioConfigurationImpl implements StudioConfiguration{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Boolean getAdvancedMode(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAdvancedMode"))
;  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


}