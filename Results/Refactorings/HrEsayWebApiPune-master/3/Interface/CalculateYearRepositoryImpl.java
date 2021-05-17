import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class CalculateYearRepositoryImpl implements CalculateYearRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public CalenderYear findByIsCurrent(int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByIsCurrent"))
    .queryParam("i",i);
  CalenderYear aux = restTemplate.getForObject(builder.toUriString(), CalenderYear.class)

 return aux;
}


}