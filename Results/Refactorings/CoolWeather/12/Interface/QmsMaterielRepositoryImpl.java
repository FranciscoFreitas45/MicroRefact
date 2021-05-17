import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsMaterielRepositoryImpl implements QmsMaterielRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsMateriel> findByUseUnitId(Integer s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByUseUnitId"))
    .queryParam("s",s);
  List<QmsMateriel> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMateriel>.class)

 return aux;
}


public List<QmsMateriel> findByPackgeUnitId(Integer s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByPackgeUnitId"))
    .queryParam("s",s);
  List<QmsMateriel> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMateriel>.class)

 return aux;
}


}