package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AgentUserRepository;
public class AgentUserRepositoryImpl implements AgentUserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<AgentUser> findByAgentnoAndOrgi(String agentno,String orgi,Sort sort){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAgentnoAndOrgi"))
    .queryParam("agentno",agentno)
    .queryParam("orgi",orgi)
    .queryParam("sort",sort)
;  List<AgentUser> aux = restTemplate.getForObject(builder.toUriString(), List<AgentUser>.class);

 return aux;
}


}