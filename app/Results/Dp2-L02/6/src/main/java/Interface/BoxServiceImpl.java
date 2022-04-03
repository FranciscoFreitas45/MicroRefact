package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.BoxService;
public class BoxServiceImpl implements BoxService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Box createSystem(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createSystem"))
;  Box aux = restTemplate.getForObject(builder.toUriString(), Box.class);

 return aux;
}


public Box saveSystem(Box box){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveSystem"))
    .queryParam("box",box)
;  Box aux = restTemplate.getForObject(builder.toUriString(), Box.class);

 return aux;
}


}