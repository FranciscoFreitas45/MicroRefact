package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SystemMessageRepository;
public class SystemMessageRepositoryImpl implements SystemMessageRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<SystemMessage> findByOrgi(String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgi"))
    .queryParam("orgi",orgi)
;  List<SystemMessage> aux = restTemplate.getForObject(builder.toUriString(), List<SystemMessage>.class);

 return aux;
}


}