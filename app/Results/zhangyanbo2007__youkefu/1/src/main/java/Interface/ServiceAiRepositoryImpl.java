package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ServiceAiRepository;
public class ServiceAiRepositoryImpl implements ServiceAiRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Page<ServiceAi> findByOrgi(String orgi,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgi"))
    .queryParam("orgi",orgi)
    .queryParam("page",page)
;  Page<ServiceAi> aux = restTemplate.getForObject(builder.toUriString(), Page<ServiceAi>.class);

 return aux;
}


}