package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SecretRepository;
public class SecretRepositoryImpl implements SecretRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<Secret> findByOrgi(String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgi"))
    .queryParam("orgi",orgi)
;  List<Secret> aux = restTemplate.getForObject(builder.toUriString(), List<Secret>.class);

 return aux;
}


}