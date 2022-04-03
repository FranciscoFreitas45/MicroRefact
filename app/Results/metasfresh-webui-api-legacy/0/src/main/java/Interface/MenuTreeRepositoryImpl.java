package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class MenuTreeRepositoryImpl implements MenuTreeRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public void cacheReset(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cacheReset"))

  restTemplate.put(builder.toUriString(), null);
}


}