package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.KlantContactPersoonService;
public class KlantContactPersoonServiceImpl implements KlantContactPersoonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public KlantContactPersoon getKCPById(long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getKCPById"))
    .queryParam("id",id)
;  KlantContactPersoon aux = restTemplate.getForObject(builder.toUriString(), KlantContactPersoon.class);

 return aux;
}


public KlantContactPersoon kcpWachtwoordWijzigen(long id,KlantContactPersoon kcp){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/kcpWachtwoordWijzigen"))
    .queryParam("id",id)
    .queryParam("kcp",kcp)
;  KlantContactPersoon aux = restTemplate.getForObject(builder.toUriString(), KlantContactPersoon.class);

 return aux;
}


}