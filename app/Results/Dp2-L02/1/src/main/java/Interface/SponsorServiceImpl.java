package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SponsorService;
public class SponsorServiceImpl implements SponsorService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Sponsor loggedSponsor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedSponsor"))
;  Sponsor aux = restTemplate.getForObject(builder.toUriString(), Sponsor.class);

 return aux;
}


public Sponsor reconstructSponsor(Sponsor Sponsor,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstructSponsor"))
    .queryParam("Sponsor",Sponsor)
    .queryParam("binding",binding)
;  Sponsor aux = restTemplate.getForObject(builder.toUriString(), Sponsor.class);

 return aux;
}


public Sponsor save(Sponsor h){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("h",h)
;  Sponsor aux = restTemplate.getForObject(builder.toUriString(), Sponsor.class);

 return aux;
}


}