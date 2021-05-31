import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class UtIndicatorClassificationsEnRepositoryImpl implements UtIndicatorClassificationsEnRepository{

 private RestTemplate restTemplate;

  String url = "http://13";


public int getSubsectorId(String subSectorName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSubsectorId"))
    .queryParam("subSectorName",subSectorName);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public List<UtIndicatorClassificationsEn> getSubsector(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSubsector"))
  List<UtIndicatorClassificationsEn> aux = restTemplate.getForObject(builder.toUriString(), List<UtIndicatorClassificationsEn>.class)

 return aux;
}


}