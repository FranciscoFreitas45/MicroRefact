package restock.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import restock.Interface.BotigaBusiness;
public class BotigaBusinessImpl implements BotigaBusiness{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Botiga getBotigaOfResponsable(Usuari usuari){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBotigaOfResponsable"))
    .queryParam("usuari",usuari)
;  Botiga aux = restTemplate.getForObject(builder.toUriString(), Botiga.class);

 return aux;
}


}