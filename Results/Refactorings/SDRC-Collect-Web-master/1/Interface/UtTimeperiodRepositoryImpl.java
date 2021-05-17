import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UtTimeperiodRepositoryImpl implements UtTimeperiodRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Object findLatestTimePeriodNId(Integer iusNid,Integer[] areaNid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findLatestTimePeriodNId"))
    .queryParam("iusNid",iusNid)
    .queryParam("areaNid",areaNid);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}