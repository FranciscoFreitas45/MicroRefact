package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.DatabaseRepository;
public class DatabaseRepositoryImpl implements DatabaseRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Database findByIdAndOrgi(String id,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndOrgi"))
    .queryParam("id",id)
    .queryParam("orgi",orgi)
;  Database aux = restTemplate.getForObject(builder.toUriString(), Database.class);

 return aux;
}


}