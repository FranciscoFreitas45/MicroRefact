import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsBomTechnologyRepositoryImpl implements QmsBomTechnologyRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsBomTechnology> findByProcessId(Integer s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByProcessId"))
    .queryParam("s",s);
  List<QmsBomTechnology> aux = restTemplate.getForObject(builder.toUriString(), List<QmsBomTechnology>.class)

 return aux;
}


}