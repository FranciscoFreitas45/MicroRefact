package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ActorService;
public class ActorServiceImpl implements ActorService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Actor getActorByUsername(String a){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getActorByUsername"))
    .queryParam("a",a)
;  Actor aux = restTemplate.getForObject(builder.toUriString(), Actor.class);

 return aux;
}


}