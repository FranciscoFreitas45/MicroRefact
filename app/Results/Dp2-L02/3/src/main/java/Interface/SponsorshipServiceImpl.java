package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SponsorshipService;
public class SponsorshipServiceImpl implements SponsorshipService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Sponsorship getRandomSponsorship(int paradeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRandomSponsorship"))
    .queryParam("paradeId",paradeId)
;  Sponsorship aux = restTemplate.getForObject(builder.toUriString(), Sponsorship.class);

 return aux;
}


public void updateSpentMoneyOfSponsorship(int paradeId,int sponsorshipId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateSpentMoneyOfSponsorship"))
    .queryParam("paradeId",paradeId)
    .queryParam("sponsorshipId",sponsorshipId)
;
  restTemplate.put(builder.toUriString(), null);
}


}