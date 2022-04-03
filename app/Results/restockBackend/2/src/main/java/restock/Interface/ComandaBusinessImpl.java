package restock.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import restock.DTO.Producte;
import restock.Interface.ComandaBusiness;
public class ComandaBusinessImpl implements ComandaBusiness{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Boolean eliminaComanda(ComandaBotiga comandaBotiga){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/eliminaComanda"))
    .queryParam("comandaBotiga",comandaBotiga);
  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


}