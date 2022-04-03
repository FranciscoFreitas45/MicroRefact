package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ConfigurationService;
public class ConfigurationServiceImpl implements ConfigurationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Configuration getConfiguration(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getConfiguration"))
;  Configuration aux = restTemplate.getForObject(builder.toUriString(), Configuration.class);

 return aux;
}


}