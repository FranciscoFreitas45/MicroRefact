import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class HolidayRepoImpl implements HolidayRepo{

 private RestTemplate restTemplate;

  String url = "http://10";


public List<Holiday> getholidaybetweendate(String fromDate,String toDate){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getholidaybetweendate"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate);
  List<Holiday> aux = restTemplate.getForObject(builder.toUriString(), List<Holiday>.class)

 return aux;
}


}