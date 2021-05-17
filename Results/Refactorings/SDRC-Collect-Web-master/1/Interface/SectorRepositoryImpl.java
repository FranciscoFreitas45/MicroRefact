import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SectorRepositoryImpl implements SectorRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<Object[]> findByIC_Type(String IC_Type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByIC_Type"))
    .queryParam("IC_Type",IC_Type);
  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class)

 return aux;
}


}