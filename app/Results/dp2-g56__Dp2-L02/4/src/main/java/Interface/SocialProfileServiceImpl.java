package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SocialProfileService;
public class SocialProfileServiceImpl implements SocialProfileService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public void deleteAllSocialProfiles(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllSocialProfiles"))
;
  restTemplate.put(builder.toUriString(), null);
}


}