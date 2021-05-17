import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UtIndicatorUnitSubgroupRepositoryImpl implements UtIndicatorUnitSubgroupRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<Object[]> getIUS(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getIUS"))
    .queryParam("id",id);
  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class)

 return aux;
}


}