package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.OptionService;
public class OptionServiceImpl implements OptionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public long getBirthday(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBirthday"))
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


}