import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class DateUtilImpl implements DateUtil{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public ZonedDateTime getDBNowDate(String dataFormat){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getDBNowDate"))
    .queryParam("dataFormat",dataFormat);
  ZonedDateTime aux = restTemplate.getForObject(builder.toUriString(), ZonedDateTime.class)

 return aux;
}


public ZonedDateTime getDBNowDate(String dataFormat){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getDBNowDate"))
    .queryParam("dataFormat",dataFormat);
  ZonedDateTime aux = restTemplate.getForObject(builder.toUriString(), ZonedDateTime.class)

 return aux;
}


public ZonedDateTime getDBNowDate(String dataFormat){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getDBNowDate"))
    .queryParam("dataFormat",dataFormat);
  ZonedDateTime aux = restTemplate.getForObject(builder.toUriString(), ZonedDateTime.class)

 return aux;
}


}