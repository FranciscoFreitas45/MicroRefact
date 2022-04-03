package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SponsorService;
public class SponsorServiceImpl implements SponsorService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Sponsor createSponsor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createSponsor"))
;  Sponsor aux = restTemplate.getForObject(builder.toUriString(), Sponsor.class);

 return aux;
}


public Sponsor reconstruct(FormObjectSponsor formObjectSponsor,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))
    .queryParam("formObjectSponsor",formObjectSponsor)
    .queryParam("binding",binding)
;  Sponsor aux = restTemplate.getForObject(builder.toUriString(), Sponsor.class);

 return aux;
}


public Sponsor saveCreate(Sponsor bro){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveCreate"))
    .queryParam("bro",bro)
;  Sponsor aux = restTemplate.getForObject(builder.toUriString(), Sponsor.class);

 return aux;
}


}