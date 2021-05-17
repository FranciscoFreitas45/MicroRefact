import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class IndicatorRepositoryImpl implements IndicatorRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public UtIndicatorEn findByIndicator_NId(int indicator_NId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByIndicator_NId"))
    .queryParam("indicator_NId",indicator_NId);
  UtIndicatorEn aux = restTemplate.getForObject(builder.toUriString(), UtIndicatorEn.class)

 return aux;
}


}