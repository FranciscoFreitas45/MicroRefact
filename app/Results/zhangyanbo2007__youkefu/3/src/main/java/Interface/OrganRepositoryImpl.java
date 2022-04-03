package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.OrganRepository;
public class OrganRepositoryImpl implements OrganRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Organ> findByOrgi(String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgi"))
    .queryParam("orgi",orgi)
;  List<Organ> aux = restTemplate.getForObject(builder.toUriString(), List<Organ>.class);

 return aux;
}


}