package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AgentServiceRepository;
public class AgentServiceRepositoryImpl implements AgentServiceRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public AgentService findByIdAndOrgi(String paramString,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndOrgi"))
    .queryParam("paramString",paramString)
    .queryParam("orgi",orgi)
;  AgentService aux = restTemplate.getForObject(builder.toUriString(), AgentService.class);

 return aux;
}


}