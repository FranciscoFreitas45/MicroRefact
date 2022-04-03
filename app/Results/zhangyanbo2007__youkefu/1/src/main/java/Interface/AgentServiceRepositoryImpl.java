package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AgentServiceRepository;
public class AgentServiceRepositoryImpl implements AgentServiceRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<AgentService> findByOrgiAndSatisfaction(String orgi,boolean satisfaction,Pageable paramPageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndSatisfaction"))
    .queryParam("orgi",orgi)
    .queryParam("satisfaction",satisfaction)
    .queryParam("paramPageable",paramPageable)
;  Page<AgentService> aux = restTemplate.getForObject(builder.toUriString(), Page<AgentService>.class);

 return aux;
}


public List<AgentService> findAll(Specification<AgentService> spec){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("spec",spec);
  List<AgentService> aux = restTemplate.getForObject(builder.toUriString(), List<AgentService>.class);

 return aux;
}


}