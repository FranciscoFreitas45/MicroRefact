package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ParadeService;
public class ParadeServiceImpl implements ParadeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void deleteInFinal(Parade p){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteInFinal"))
    .queryParam("p",p)
;
  restTemplate.put(builder.toUriString(), null);
}


public void deleteParadetest(Parade parade){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteParadetest"))
    .queryParam("parade",parade)
;
  restTemplate.put(builder.toUriString(), null);
}


}