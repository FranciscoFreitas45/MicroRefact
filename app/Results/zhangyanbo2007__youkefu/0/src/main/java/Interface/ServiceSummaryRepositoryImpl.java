package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ServiceSummaryRepository;
public class ServiceSummaryRepositoryImpl implements ServiceSummaryRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Page<AgentServiceSummary> findByOrgiAndUserid(String orgi,String userid,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndUserid"))
    .queryParam("orgi",orgi)
    .queryParam("userid",userid)
    .queryParam("pageable",pageable)
;  Page<AgentServiceSummary> aux = restTemplate.getForObject(builder.toUriString(), Page<AgentServiceSummary>.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public AgentServiceSummary findByIdAndOrgi(String id,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndOrgi"))
    .queryParam("id",id)
    .queryParam("orgi",orgi);
  AgentServiceSummary aux = restTemplate.getForObject(builder.toUriString(), AgentServiceSummary.class);

 return aux;
}


}