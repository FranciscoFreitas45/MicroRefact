import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class InStorageRepositoryImpl implements InStorageRepository{

 private RestTemplate restTemplate;

  String url = "http://4";


public Float findInStorageOrderByDay(String dateString){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findInStorageOrderByDay"))
    .queryParam("dateString",dateString);
  Float aux = restTemplate.getForObject(builder.toUriString(), Float.class)

 return aux;
}


}