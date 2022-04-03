package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ProcessRestControllerImpl implements ProcessRestController{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void cacheReset(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cacheReset"))

  restTemplate.put(builder.toUriString(), null);
}


}