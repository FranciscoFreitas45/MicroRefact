import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsUnitRepositoryImpl implements QmsUnitRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsUnit> findByUnitCd(String s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByUnitCd"))
    .queryParam("s",s);
  List<QmsUnit> aux = restTemplate.getForObject(builder.toUriString(), List<QmsUnit>.class)

 return aux;
}


public List<QmsUnit> findByUnitCd(String s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByUnitCd"))
    .queryParam("s",s);
  List<QmsUnit> aux = restTemplate.getForObject(builder.toUriString(), List<QmsUnit>.class)

 return aux;
}


}