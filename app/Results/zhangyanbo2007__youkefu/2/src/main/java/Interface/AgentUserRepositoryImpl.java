package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AgentUserRepository;
public class AgentUserRepositoryImpl implements AgentUserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public AgentUser findOneByAgentnoAndStatusAndOrgi(String id,String status,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneByAgentnoAndStatusAndOrgi"))
    .queryParam("id",id)
    .queryParam("status",status)
    .queryParam("orgi",orgi)
;  AgentUser aux = restTemplate.getForObject(builder.toUriString(), AgentUser.class);

 return aux;
}


}