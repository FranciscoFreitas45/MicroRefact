import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class HolidayRepoImpl implements HolidayRepo{

 private RestTemplate restTemplate;

  String url = "http://10";


public List<Holiday> findByExInt1AndDelStatus(int hoCatId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByExInt1AndDelStatus"))
    .queryParam("hoCatId",hoCatId)
    .queryParam("i",i);
  List<Holiday> aux = restTemplate.getForObject(builder.toUriString(), List<Holiday>.class)

 return aux;
}


}