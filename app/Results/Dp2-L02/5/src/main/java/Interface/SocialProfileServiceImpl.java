package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SocialProfileService;
public class SocialProfileServiceImpl implements SocialProfileService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public SocialProfile save(SocialProfile socialProfile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("socialProfile",socialProfile)
;  SocialProfile aux = restTemplate.getForObject(builder.toUriString(), SocialProfile.class);

 return aux;
}


}