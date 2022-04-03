package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SponsorService;
public class SponsorServiceImpl implements SponsorService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public void deleteSponsor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteSponsor"))

  restTemplate.put(builder.toUriString(), null);
}


}