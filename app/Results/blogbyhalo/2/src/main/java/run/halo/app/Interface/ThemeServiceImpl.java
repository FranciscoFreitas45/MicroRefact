package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.ThemeService;
public class ThemeServiceImpl implements ThemeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://15";


public Path getBasePath(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBasePath"))
;  Path aux = restTemplate.getForObject(builder.toUriString(), Path.class);

 return aux;
}


}