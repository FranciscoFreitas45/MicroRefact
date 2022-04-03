package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AgentUserContactsRepository;
public class AgentUserContactsRepositoryImpl implements AgentUserContactsRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<AgentUserContacts> findByUseridAndOrgi(String userid,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUseridAndOrgi"))
    .queryParam("userid",userid)
    .queryParam("orgi",orgi)
;  List<AgentUserContacts> aux = restTemplate.getForObject(builder.toUriString(), List<AgentUserContacts>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}